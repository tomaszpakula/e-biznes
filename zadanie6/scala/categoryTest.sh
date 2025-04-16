echo -e "Get all categories\n"
curl -s -X GET http://localhost:9000/categories | jq

echo -e "Get category by id (category exist)\n"
curl -s -X GET http://localhost:9000/categories/1 | jq

echo -e "Get category by id (category doesn't exist)\n"
curl -s -X GET http://localhost:9000/categories/10 | jq

echo -e "Create new category\n"
curl -s -X POST http://localhost:9000/categories -H "Content-Type: application/json" -d '{"id" : 3, "name" : "home"}' | jq

echo -e "Update category with id 2\n"
curl -s -X PUT http://localhost:9000/categories/2 -H "Content-Type: application/json" -d '{"id" : 2, "name" : "food"}' | jq

echo -e "Get all categories\n"
curl -s -X GET http://localhost:9000/categories | jq

echo -e "Delete category with id 2\n"
curl -s -X DELETE http://localhost:9000/categories/2 | jq

echo -e "Get all categories\n"
curl -s -X GET http://localhost:9000/categories | jq