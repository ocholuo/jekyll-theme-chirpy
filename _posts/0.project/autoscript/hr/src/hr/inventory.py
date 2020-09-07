
# [{
#     "name": "kevin",
#     "groups": ["wheel", "dev"],
#     "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
#   },
#   {
#     "name": "lisa",
#     "groups": ["wheel"],
#     "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
#   },
#   {
#     "name": "jim",
#     "groups": [],
#     "password": "$6$HXdlMJqcV8LZ1DIF$LCXVxmaI/ySqNtLI6b64LszjM0V5AfD.ABaUcf4j9aJWse2t3Jr2AoB1zZxUfCr8SOG0XiMODVj2ajcQbZ4H4/"
#   }]

import grp
import json
import spwd

from .helpers import user_names

def load(inv_file.name)
    with open(path) as f:
        return json.load(f)
        
def _groups_for_user(user_name):
    return [g.gr_name for g in grp.getgrall() if user_name in g.gr_mem]

def dump(path, user_names=user_names()):
    users = []
    for user_name in user_names:
        password = spwd.getspnam(user_name).sp_pwd
        groups = _groups_for_user(user_name)
        users.append({
            'name': user_name,
            'groups': groups,
            'password': password
        })
    with open(path, 'w') as f:
        json.dump(users, f)















#
