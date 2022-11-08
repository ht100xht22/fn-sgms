#!/usr/bin/bash

if [ $( docker ps -a | grep fn_sgms_db | wc -l ) -gt 0 ]; then
	echo "Stopping database..."
	docker stop fn_sgms_db
	echo "Database is stopped!"
	return 0
fi
echo "Database could not be found!"
return 0
