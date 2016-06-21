echo  "Start -------------------------------->"

docker rm -f loginms
docker rmi loginms 

docker rm -f haproxylb 
docker rmi haproxylb 

docker rm -f blogms 
docker rmi blogms 

rm -r demo2
mkdir demo2
cd demo2
git clone https://github.com/SandeepPamujula/BlogAppMicroServices.git

echo  "$$$ Create Login ms docker container $$$"
cd BlogAppMicroServices/LoginMicroService
mvn clean install
#cp ../config/cluster_login.xml ./target/classes/cluster.xml
docker build --tag loginms .

echo  "$$$ Create Blog ms docker container $$$"
cd ../BlogMicroService
mvn clean install
#cp ../config/cluster_blog.xml ./target/classes/cluster.xml
docker build --tag blogms .

echo  "$$$ Create haproxy docker container $$$"
cd ../blogapp_haproxy
docker build --tag haproxylb .


echo  "End -------------------------------->"
