mkdir certs

cd certs

openssl genrsa -des3 -out ca.key 4096
#Remove passphrase for example purposes
openssl rsa -in ca.key -out ca.key
openssl req -new -x509 -days 3650 -key ca.key -subj "/CN=*.your.hostname" -out ca.crt

printf test > passphrase.txt
openssl genrsa -des3 -passout file:passphrase.txt -out server.key 2048
openssl req -new -passin file:passphrase.txt -key server.key -subj "/CN=*.your.hostname" -out server.csr

openssl x509 -req -days 365 -in server.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out server.crt

cd ../

docker run --rm --name some-nginx -p 443:443 -v $(pwd)/certs/server.key:/etc/nginx/certs/tls.key -v $(pwd)/certs/server.crt:/etc/nginx/certs/tls.crt -v $(pwd)/nginx.conf:/etc/nginx/conf.d/nginx.conf -v $(pwd)/certs/passphrase.txt:/etc/nginx/certs/password nginx