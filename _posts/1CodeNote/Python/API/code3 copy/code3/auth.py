from flask import Blueprint, render_template, request, url_for, redirect, make_response, jsonify
from werkzeug.security import generate_password_hash, check_password_hash
from .models import User
from . import db
from flask_login import login_user, logout_user, login_required

auth = Blueprint('auth', __name__)

@auth.route('/signup')
def signup():
   return render_template('signup.html')
# http GET http://127.0.0.1:5000/signup


@auth.route('/signup', methods=['POST'])
def signup_post():
   # got the signup info
   content = request.json
   username = content['username']
   fLname = content['Firstname Lastname']
   password = content['password']
   engine = content['Mother’s Favorite Search Engine']
   # check the username, usery the database, get the first one
   user = User.query.filter_by(user_username=username).first()
   if user:
      print("username already Exists")
   new_user = User(
      user_username=username, 
      user_flname=fLname, 
      user_password=generate_password_hash(password, method='sha256'),
      user_engine=engine
      )
   db.session.add(new_user)
   db.session.commit()
   # return render_template('signup.html')
   # return redirect(url_for('auth.login'))
   return make_response(jsonify(content), 201)
# echo '{"username":"a",  "Firstname Lastname":"ab", "password":"123",  "Mother’s Favorite Search Engine":"c"}' | http POST http://127.0.0.1:5000/signup
# echo '{"username":"ab",  "Firstname Lastname":"cd", "password":"123",  "Mother’s Favorite Search Engine":"c"}' | http POST http://127.0.0.1:5000/signup
# echo '{"username":"abc",  "Firstname Lastname":"cde", "password":"123",  "Mother’s Favorite Search Engine":"c"}' | http POST http://127.0.0.1:5000/signup





@auth.route('/login')
def login():
   return render_template('login.html')
# http GET http://127.0.0.1:5000/login

@auth.route('/login', methods=['POST'])
def login_post():
   content = request.json
   username = content['username']
   password = content['password']
   # whether the user and passwd correct:
   user = User.query.filter_by(user_username=username).first()
   if not user or not check_password_hash(user.user_password, password):
      # return redirect("auth.login")
      return make_response(jsonify({"Sucessfule login": False}), 404)   
   # correct username and passwd:
   remember = False
   login_user(user, remember=remember)
   # return redirect(url_for('main.profile'))
   return make_response(jsonify({"Sucessfule login": True}), 200)
# echo '{"username":"a", "password":"123"}' | http POST http://127.0.0.1:5000/login
# echo '{"username":"a", "password":"wrongpasswd"}' | http POST http://127.0.0.1:5000/login
# echo '{"username":"abc", "password":"123"}' | http POST http://127.0.0.1:5000/login
# echo '{"username":"abc", "password":"wrongpasswd"}' | http POST http://127.0.0.1:5000/login



@auth.route('/logout')
@login_required
def logout():
   logout_user()
   # return redirect('main.index')
   return make_response(jsonify({"Session login": "logout"}), 201)

# http GET http://127.0.0.1:5000/logout