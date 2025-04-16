FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
WORKDIR /app
COPY . .
COPY . .
RUN rm -rf target/ 
EXPOSE 9000
CMD sbt run