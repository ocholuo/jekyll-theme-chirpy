
# 24.14. Project - OMDB and TasteDive

[toc]

Final Project of the Data Collection and Processing with Python.

This course is part of the Python 3 Programming Specialization offer by University of Michigan in Coursera. [24.14. Project - OMDB and TasteDive](https://fopp.umsi.education/books/published/fopp/InternetAPIs/chapterProject.html)

---

## Assignment

mashing up data from two different APIs to make movie recommendations.

The `TasteDive API` lets you provide a movie (or bands, TV shows, etc.) as a query input, and returns a set of related items.

The `OMDB API` lets you provide a movie title as a query input and get back data about the movie, including scores from various review sites (Rotten Tomatoes, IMDB, etc.).

[The documentation for the API](https://www.omdbapi.com/)

use `TasteDive` to get related movies for a whole list of titles. combine the resulting lists of related movies, and sort them according to their Rotten Tomatoes scores (which will require making API calls to the OMDB API.)

[The documentation for the API](https://tastedive.com/read/api.)


To avoid problems with rate limits and site accessibility, we have provided a cache file with results for all the queries you need to make to both OMDB and TasteDive.

Just use `requests_with_caching.get()` rather than `requests.get()`.

If you’re having trouble, you may not be formatting your queries properly, or you may not be asking for data that exists in our cache. We will try to provide as much information as we can to help guide you to form queries for which data exists in the cache.

---

1. Define function `get_movies_from_tastedive()`
    - fetch data from TasteDive. [The documentation for the API](https://tastedive.com/read/api).

    - take one input parameter, a string that is the name of a movie or music artist.
    - return the 5 TasteDive results that are associated with that string; be sure to only get `movies`, not other kinds of media. It will be a python dictionary with just one key, ‘Similar’.

    > This retrieves recommendations for Red Hot Chili Peppers and Pulp Fiction:
    > https://tastedive.com/api/similar?q=red+hot+chili+peppers%2C+pulp+fiction

    > PARAMETERS
    > q: the search query; consists of a series (at least one) of bands, movies, TV shows, podcasts, books, authors and/or games, separated by commas. Sometimes it is useful to specify the type of a certain resource in the query (e.g. if a movie and a book share the same title).
    "band:", "movie:", "show:", "podcast:", "book:", "author:" "game:" operators
    example: "band:underworld, movie:harry potter, book:trainspotting".
    Don't forget to encode this parameter.

    > type: query type, specifies the desired type of results. (music, movies, shows, podcasts, books, authors, games). If not specified, the results can have mixed types.

    > limit: maximum number of recommendations to retrieve. Default is 20.


2. write function `extract_movie_titles`, that extracts just the list of movie titles from a dictionary returned by `get_movies_from_tastedive`.

3. write function `get_related_titles`. It takes a list of movie titles as input. It gets five related movies for each from TasteDive, extracts the titles for all of them, and combines them all into a single list. Don’t include the same movie twice.

4. fetch data from OMDB. Define function `get_movie_data`. It takes in one parameter which is a string that should represent the title of a movie you want to search. The function should return a dictionary with information about that movie.

> Send all data requests to:
> http://www.omdbapi.com/?apikey=[yourkey]&
> t	Optional*		<empty>	Movie title to search for.

5. write function `get_movie_rating`. It takes an OMDB dictionary result for one movie and extracts the Rotten Tomatoes rating as an integer. For example, if given the OMDB dictionary for “Black Panther”, it would return 97. If there is no Rotten Tomatoes rating, return 0.


6. Define function `get_sorted_recommendations`. It takes a list of movie titles as an input. It returns a sorted list of related movie titles as output, up to five related movies for each input movie title. The movies should be sorted in descending order by their Rotten Tomatoes rating, as returned by the get_movie_rating function. Break ties in reverse alphabetic order, so that ‘Yahşi Batı’ comes before ‘Eyyvah Eyvah’.



```py

import requests_with_caching
import json

def get_movies_from_tastedive(name):
    baseurl="https://tastedive.com/api/similar"
    params={}
    params["q"]= name
    params["type"]="movies"
    #params["k"]="327878-course3p-I4ZNBN4A"
    params["limit"]=5
    page=requests_with_caching.get(baseurl,params)
#    print(page.url)
    dic=page.json()
#    print(dic)
    return dic
#
#https://tastedive.com/api/similar?q=Tony+Bennett&type=movies&limit=5
#<class 'dict'>
#{
#  'Similar':
#  {
#    'Info': [{'Name': 'Tony Bennett', 'Type': 'music'}],
#    'Results': [{'Name': 'The Startup Kids', 'Type': 'movie'},
#    {'Name': 'Charlie Chaplin', 'Type': 'movie'},
#    {'Name': 'Venus In Fur', 'Type': 'movie'},
#    {'Name': 'Loving', 'Type': 'movie'},
#    {'Name': 'The African Queen', 'Type': 'movie'}]
#  }
#}


# input the dic with 5 movies
def extract_movie_titles(dic):
    titleslst=[subdic['Name'] for subdic in dic['Similar']['Results']]
    return titleslst
#    titleslst=[subdic["Name"] for subdic in dic["Similar"]['Results']]
#    print(titleslst)
#    return titleslst
#['The Startup Kids', 'Charlie Chaplin', 'Venus In Fur', 'Loving', 'The African Queen']

def get_related_titles(lst):
    titlelst=[]
    for name in lst:
        [titlelst.append(name) for name in extract_movie_titles(get_movies_from_tastedive(name)) if name not in titlelst]
#        print(name)
#        bigdic=get_movies_from_tastedive(name)
#        print(bigdic)
#        titlelst=extract_movie_titles(get_movies_from_tastedive(name))
#        print(titlelst)
    return titlelst


def get_movie_data( name ):
    baseurl="http://www.omdbapi.com/"
    params={}
    params["t"]= name
    params["r"]= "json"
    page=requests_with_caching.get(baseurl,params)
#    print(page.url)
    dic=page.json()
#    print(dic)
    return dic
#
# {'Type': 'movie', 'Title': 'Venom', 'Year': '2018', 'Rated': 'N/A',
# 'Ratings': [{'Source': 'Internet Movie Database', 'Value': '6.9/10'}, {'Source': 'Metacritic', 'Value': '35/100'}],
# 'Metascore': '35',
# 'imdbRating': '6.9', ..., 'Response': 'True'}


def get_movie_rating(ratingdic):
    num=0
    for rate in ratingdic['Ratings']:
        if rate['Source'] =='Rotten Tomatoes':
            num=int(rate['Value'][:-1])
    return num


def get_sorted_recommendations(namelist):
    relatelst=get_related_titles(namelist)
#    print(relatelst)
    newlst=sorted(relatelst, reverse=True, key=lambda x:(get_movie_rating(get_movie_data(x)),x) )
#    print(newlst)
    return newlst
```
















.
