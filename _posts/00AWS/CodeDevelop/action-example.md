




```yml
name: 'Automatic build'
on:                     # setup the way to be triggered
  push:                 # when push
    branches:           # which brances will trigger 
      - master
    paths-ignore:       # which path will not trigger 
      - '.gitignore'
      - 'README.md'
      - 'LICENSE'

jobs:
  build-n-test:                          # job 1 name             
    runs-on: ubuntu-latest

    steps:                              # the test step
      - uses: actions/setup-ruby@v1     # run official "actions/checkout@v2" code to setup the environment
        with:
          ruby-version: '2.6.x'

      - name: Checkout
        uses: actions/checkout@v2       # run official "actions/checkout@v2" code to copy the current code 
        with:
          fetch-depth: 0

      - name: Bundle Caching
        id: bundle-cache
        uses: actions/cache@v1
        with:
          path: vendor/bundle
          key: ${{ runner.os }}-gems-${{ hashFiles('**/Gemfile') }}
          restore-keys: |
            ${{ runner.os }}-gems-

      - name: Bundle config                    # name the shell
        run: |                                 # the cmd to run sript
          bundle config path vendor/bundle

      - name: Bundle Install
        if: steps.bundle-cache.outputs.cache-hit != 'true'
        run: |
          bundle install

      - name: Bundle Install locally
        if: steps.bundle-cache.outputs.cache-hit == 'true'
        run: |
          bundle install --local

      - name: Build Site
        run: |
          bash tools/build.sh -b ""

      - name: Test Site
        run: |
          bash tools/test.sh

  deploy:                       # job 2 name
    needs: build-n-test         # needs job 1 to be run first for deploy to be run
    runs-on: ubuntu-latest

    steps:
      - uses: actions/setup-ruby@v1
        with:
          ruby-version: '2.6.x'

      - name: Checkout
        uses: actions/checkout@v2
        with:
          fetch-depth: 0

      - name: Bundle Caching
        id: bundle-cache
        uses: actions/cache@v1
        with:
          path: vendor/bundle
          key: ${{ runner.os }}-gems-${{ hashFiles('**/Gemfile') }}
          restore-keys: |
            ${{ runner.os }}-gems-

      - name: Bundle config
        run: |
          bundle config path vendor/bundle

      - name: Bundle Install
        if: steps.bundle-cache.outputs.cache-hit != 'true'
        run: |
          bundle install

      - name: Bundle Install locally
        if: steps.bundle-cache.outputs.cache-hit == 'true'
        run: |
          bundle install --local

      - name: Build site
        run: |
          bash tools/build.sh

      - name: Deploy
        run: |
          bash tools/deploy.sh
```











.