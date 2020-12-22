from flask import Flask, make_response, jsonify, request
from flask import Blueprint, render_template

main = Blueprint('main', __name__)


@main.route('/')
def index():
    return "Hello, World!"


@main.route('/profile')
def profile():
    return "Profile Here!"