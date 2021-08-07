#!/bin/bash
DB_NAME=${db_user}
DB_USER=${notification}
DB_USER_PASS=${root}
su postgres
CREATE DATABASE $DB_NAME;
psql -c "CREATE USER $DB_USER WITH PASSWORD '$DB_USER_PASS';"
psql -c "grant all privileges on database $DB_NAME to $DB_USER;"
echo "Postgres User '$DB_USER' and database '$DB_NAME' created."
EOF
