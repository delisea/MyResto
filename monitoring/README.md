based on :
https://github.com/nicolargo/docker-influxdb-grafana.git

usage :
- docker-compose up -d
- go on localhost:3000
- log into grafana with admin:admin
- create datasource => type : InfluxDB ; name : InfluxDB ; access : direct ; database : telegraf
- create dashboard by importing json files
