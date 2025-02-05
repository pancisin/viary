FROM node:lts-alpine AS build

WORKDIR /dist

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx

COPY nginx.conf /etc/nginx/nginx.conf

WORKDIR /app

COPY --from=build /dist/src/main/resources/static /usr/share/nginx/html

EXPOSE 80