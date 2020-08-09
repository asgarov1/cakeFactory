# cakeFactory

1) To start Postgres in Docker - open terminal from top level folder (where Dockerfile is) and run:  
<code>docker build -t cakefactory-postgres . && docker run --rm -d -p 5432:5432 cakefactory-postgres</code>

If docker doesn't run check that port 5432 is available  

2) Run Spring Application and pass password as environment variable  
<code>./gradlew bootRun --args='--spring.datasource.password=password'</code>

Keep in mind that alternative login options (login with google, github, facebook) won't work because the oath ids/secrets 
are passed as commandline and you won't have them (nor will I disclose these:)