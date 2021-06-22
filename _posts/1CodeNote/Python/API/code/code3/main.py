from flask import Blueprint, render_template, make_response, jsonify
from flask_login import login_required, current_user
from .models import User

main = Blueprint('main', __name__)

@main.route('/', methods=['GET'])
def index():
    # return render_template('index.html')
    return make_response("Home index Page", 200)
# http GET http://127.0.0.1:5000/


@main.route('/profile', methods=['GET'])
@login_required
def profile():
    # return render_template('profile.html', name=current_user.name)
    return make_response("User profile Page", 200)
# http GET http://127.0.0.1:5000/profile


@main.route('/userinfo', methods=['GET'])
# @login_required
def user_info():
    target_id = 1
    user = User.query.filter_by(db_id=target_id).first()
    username = user.db_username 
    flname = user.db_flname
    engine = user.db_engine

    return make_response(jsonify({"username": username, "Firstname Lastname":flname, "Mother’s Favorite Search Engine" :engine}), 201)

    # if not current_user.is_anonymous:
    #     user = User.query.filter_by(db_id=current_user.db_id).first()
    #     username = user.db_username 
    #     flname = user.db_flname
    #     engine = user.db_engine
    #     return make_response(jsonify({"username": username, "flname":flname, "engine" :engine}), 200)
    # else:
    #     return make_response(jsonify({{"status": 401, "userinfo":False, "error message":"please login first"}), 401)
# http GET http://127.0.0.1:5000/userinfo

