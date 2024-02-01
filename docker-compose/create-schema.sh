USER=sysdba
PASSWORD=change-it
DATABASE=MYPOCDB

systemctl stop firewalld.service
/usr/local/firebird/bin/isql -user "$USER" -password "$PASSWORD" -input '/firebird/database.sql' "localhost:$DATABASE"
rm /firebird/database.sql
