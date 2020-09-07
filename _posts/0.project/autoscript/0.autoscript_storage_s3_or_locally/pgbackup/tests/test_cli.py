

import pytest
from pgbackup import cli

@pytest.fixture
def parser():
    return cli.create_parser()

url = "postgres://bob:password@example.com:5432/db_one"

def test_parser_without_driver(parser):
    "Without a specified driver the parser will exit"
    with pytest.raises(SystemExit):
        # parser = cli.create_parser()
        parser.parse_args([url])

def test_parser_with_driver(parser):
    "The parser will exit if it receives a driver without a destination"
    # parser = cli.create_parser()
    with pytest.raises(SystemExit):
        parser.parse_args([url, "--driver", "local"])

def test_parser_with_unknown_drivers(parser):
    "The parser will exit if the driver name is unknown."
    # parser = cli.create_parser()
    with pytest.raises(SystemExit):
        parser.parse_args([url, "--driver", "azure", "destination"])

def test_parser_with_known_drivers(parser):
    "The parser will not exit if the driver name is known."
    # parser = cli.create_parser()
    for driver in ['local', 's3']:
        assert parser.parse_args([url, "--driver", driver, "destination"])

def test_parser_with_driver_and_destination(parser):
    " The parser will not exit if it receives a driver with a destination"
    # parser = cli.create_parser()
    args = parser.parse_args([url, "--driver","local","/some/path"])
    assert args.driver == "local"
    assert args.destination == "/some/path"
