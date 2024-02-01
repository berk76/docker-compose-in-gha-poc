USER=sysdba
PASSWORD=change-it
DATABASE=MYPOCDB

/usr/local/firebird/bin/isql -user "$USER" -password "$PASSWORD" -input '/firebird/database.sql' "poc-db:$DATABASE"
rm /firebird/database.sql
