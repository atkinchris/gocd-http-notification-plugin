FROM node:alpine

ENV NODE_ENV=production
WORKDIR /app

ADD ./ /app
RUN yarn --pure-lockfile

ENTRYPOINT ["yarn"]
CMD ["start"]
