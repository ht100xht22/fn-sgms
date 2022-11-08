#!/usr/bin/bash

if [ $( docker ps -a | grep fn_sgms_db | wc -l ) -lt 1 ]; then
	echo "Creating database..."
	docker run -it -d -p 5432:5432 --rm --volume=$(pwd):/data --workdir=/data --name=fn_sgms_db \
	-e POSTGRES_USERNAME=postgres \
	-e POSTGRES_PASSWORD=postgres \
	-e POSTGRES_DB="sgms" \
	postgres:13-alpine
	echo "Database has been created!"
	return 0;
fi
echo "Database is already created!"
return 0;
