from menu import *
from database_manager import *


def main():
    print("\COPY (SELECT * FROM accountlog) TO '/home/vejayd/Desktop/Password-Manager/passwords.csv' DELIMITER ',' CSV HEADER;")
    login()
    #connection()
    #table_creation()


if __name__ == "__main__":
    main()
