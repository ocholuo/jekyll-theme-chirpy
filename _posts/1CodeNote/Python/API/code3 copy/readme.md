

pip3 install flask_sqlalchemy
pip3 install flask_login

python3
from code3 import db, create_app
db.create_all(app=create_app())
