FROM node:22 AS build-env
COPY . /app
WORKDIR /app

RUN npm install
RUN npm run build --configuration=production

FROM gcr.io/distroless/nodejs24-debian12:nonroot
COPY --from=build-env /app/dist           /app/dist
COPY --from=build-env /app/node_modules   /app/node_modules
COPY --from=build-env /app/package.json   /app/package.json
COPY --from=build-env /app/server.js      /app/server.js
WORKDIR /app
EXPOSE 4200
CMD ["server.js"]
