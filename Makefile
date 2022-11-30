init: scripts/init.sh
	@echo "Initializing OpenFaaS"
	bash scripts/init.sh

run:scripts/init-fn.sh
	@echo "Initializing Functions"
	bash scripts/init-fn.sh