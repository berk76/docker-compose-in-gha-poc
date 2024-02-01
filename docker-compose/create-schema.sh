USER=sysdba
PASSWORD=change-it
DATABASE=MYPOCDB

sleep 1s
cat /etc/hosts
/usr/local/firebird/bin/isql -user "$USER" -password "$PASSWORD" -input '/firebird/database.sql' "host.docker.internal:$DATABASE"
rm /firebird/database.sql
