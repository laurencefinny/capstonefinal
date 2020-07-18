FROM node:latest

# Create app directory
RUN mkdir -p /fms-ui/src/app
WORKDIR /fms-ui/src/app

COPY package.json /fms-ui/src

RUN npm install

CMD [ "npm", "start" ]