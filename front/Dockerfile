FROM nginx:1.26.3-alpine3.20

COPY br.albatross.key.pem /etc/nginx/
COPY br.albatross.cert.pem /etc/nginx/

COPY nginx_template.conf /etc/nginx/conf.d/default.conf

RUN ["rm", "-rf", "/usr/share/html/*"]

COPY ./dist/favicon.ico /usr/share/nginx/html/
COPY ./dist/index.html /usr/share/nginx/html/
COPY ./dist/css /usr/share/nginx/html/
COPY ./dist/js /usr/share/nginx/html/
