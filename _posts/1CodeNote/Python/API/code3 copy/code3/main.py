from flask import Blueprint, render_template, make_response, jsonify
from flask_login import login_required, current_user
from .models import User

main = Blueprint('main', __name__)

@main.route('/')
def index():
    return render_template('index.html')

@main.route('/profile')
@login_required
def profile():
    return render_template('profile.html', name=current_user.name)


@main.route('/userinfo')
@login_required
def user_info():
    user = User.query.filter_by(db_username=current_user.db_username).first_or_404()
    username = user.db_username 
    flname = user.user_flname 
    engine = user.user_engine
    return make_response(jsonify({"username": username, "flname":flname, "engine" :engine}), 201)
# http POST http://127.0.0.1:5000/userinfo

