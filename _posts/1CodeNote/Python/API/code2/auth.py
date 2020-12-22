from flask import Blueprint, render_template

auto = Blueprint('auth', __name__)


@auth.route('/signup')
def signup():
   return "This page for signup"



@auth.route('/login')
def login():
   return "This page for login"



@auth.route('/logout')
def logout():
   return "This page for logout"