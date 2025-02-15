FROM nginx:1.26.3-alpine3.20

COPY br.albatross.key.pem /etc/nginx/
COPY br.albatross.cert.pem /etc/nginx/

COPY br.albatross.key.pem /etc/nginx/nginx.conf
COPY br.albatross.cert.pem /etc/nginx/nginx.conf

COPY nginx_template.conf /etc/nginx/nginx.conf

RUN ["rm", "-rf", "/usr/share/html/*"]

COPY ./dist/ /usr/share/nginx/html/