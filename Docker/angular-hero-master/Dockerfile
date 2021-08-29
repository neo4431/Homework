# Stage 1
FROM node:13-alpine AS angularBuild

WORKDIR /app

COPY package.json ./

RUN npm install

COPY . .

RUN npm run build

# Stage 2

FROM nginx:1.17-alpine

WORKDIR /app

COPY --from=angularBuild /app/dist /usr/share/nginx/html