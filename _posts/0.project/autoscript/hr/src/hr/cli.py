
# 8. Exercise: Test Drive Building a CLI Parser
# The ideal usage of the hr command is this:
# $ hr path/to/inventory.json
# Adding user 'kevin'
# Added user 'kevin'
# Updating user 'lisa'
# Updated user 'lisa'
# Removing user 'alex'
# Removed user 'alex'

# The alternative usage of the CLI will be to pass a --export flag like so:
# $ hr --export path/to/inventory.json
# --export flag won’t take any arguments.
# Instead, default the value of this field to False, and set the value to True if the flag is present.

# Write tests before implementing a CLI parser. Ensure the following:
# An error is raised if no arguments are passed to the parser.
# No error is raised if a path is given as an argument.
# The export value is set to True if the --export flag is given.

import argparse

def create_parser():
    parser = argparse.ArgumentParser()
    parser.add_argument('path', help='path to inventory file (JSON).')
    parser.add_argument('--export', action='store_true', help='export current settings to inventory file.')
    return parser

def main():
    from hr import inventory, users
    args = create_parser().parse_args()
    if args.export:
        inventory.dump(args.path)
    else:
        users_info = inventory.load(args.path)
        users.sync(users_info)



#
