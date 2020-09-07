
# 9. Exercise: Implementing User Management

# The tool you’re building is going to be running on Linux systems, and it’s safe to assume that it’s going to run via sudo.
# With this information, it’s safe to say that the tool can utilize usermod, useradd, and userdel to keep users on the server up to date.

# Create a module in your package to work with user information.
# You’ll want to be able to do the following:
# - Received a list of user dictionaries and ensure that the system’s users match.
# - Have a function that can create a user with the given information if no user exists by that name.
# - Have a function that can update a user based on a user dictionary.
# - Have a function that can remove a user with a given username.
# - The create, update, and remove functions should print that they are creating/updating/removing the user before executing the command.
# The user information will come in the form of a dictionary shaped like this:

{
  'name': 'kevin',
  'groups': ['wheel', 'dev'],
  'password': '$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/'
}
The password values will be SHA512 encrypted.

Hint: You can generate an encrypted password in Python that is usable with usermod -p with this snippet:

import crypt

crypt.crypt('password', crypt.mksalt(crypt.METHOD_SHA512))
Tools to Consider:
You’ll likely want to interface with the following Unix utilities:

useradd
usermod
userdel
Python modules you’ll want to research:

pwd - Password/User database.
grp - Group database.
Be careful in testing not to delete your own user or change your password to something that you don’t know.
