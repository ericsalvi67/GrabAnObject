# GrabAnObject — quick integration guide

This repository contains a small Java project demonstrating a DDD-like layout and JDBC persistence to PostgreSQL.

## Setup

1. Ensure you have JDK 17 installed.
2. Put the PostgreSQL JDBC driver jar in the `lib/` folder (e.g. `lib/postgresql-42.5.0.jar`).
3. Create a PostgreSQL database and run the migration:
   ```ps1
   psql "postgresql://<user>:<password>@<host>:<port>/<db>" -f db/migrations/V1__create_tables.sql
   ```

## Run the integration test

Set environment variables and run the integration class with javac/java:

```ps1
$env:DB_URL = 'jdbc:postgresql://localhost:5432/yourdb'
$env:DB_USER = 'yourusername'
$env:DB_PASSWORD = 'yourpassword'

# compile
javac -d bin -cp "lib/*;src" @(Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })

# run
java -cp "lib/*;bin" integration.DbIntegrationTest
```

The integration runner will insert a type, a user, an object and a maintenance record, then print lists.

## Notes
- The integration runner is minimal and intended for local testing only. It will insert sample rows.
- Adjust cleanup/ID handling as needed; the runner does not delete the inserted rows.
- If driver methods for `OffsetDateTime` aren't supported, the code falls back to `Timestamp` mapping.

