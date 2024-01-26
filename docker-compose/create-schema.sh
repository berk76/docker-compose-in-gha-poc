USER=sysdba
PASSWORD=change-it
DATABASE=MYPOCDB

/usr/local/firebird/bin/isql -user "$USER" -password "$PASSWORD" -input '/firebird/database.sql' "127.0.0.1:$DATABASE"
rm /firebird/database.sql
