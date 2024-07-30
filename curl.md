# MealRestController API

## 1. Get All Meals

```sh
curl -X GET "http://localhost:8080/topjava/rest/meals" -H "Accept: application/json"
```

## 2. Get a Meal by ID

Replace {id} with the actual meal ID.

```sh
curl -X GET "http://localhost:8080/topjava/rest/meals/{id}" -H "Accept: application/json"
```

## 3. Create a New Meal

Replace the JSON data with the actual meal data you want to create.

```sh
curl -X POST "http://localhost:8080/topjava/rest/meals" \
-H "Content-Type: application/json" \
-d '{
"dateTime": "2024-07-30T12:00:00",
"description": "Lunch",
"calories": 600
}'
```

## 4. Update an Existing Meal

Replace {id} with the actual meal ID and the JSON data with the updated meal data.

```sh
curl -X PUT "http://localhost:8080/topjava/rest/meals/{id}" \
-H "Content-Type: application/json" \
-d '{
"dateTime": "2024-07-30T13:00:00",
"description": "Updated Lunch",
"calories": 700
}'
```

## 5. Delete a Meal

Replace {id} with the actual meal ID.

```sh
curl -X DELETE "http://localhost:8080/topjava/rest/meals/{id}"
```

## 6. Get Meals Between Dates and Times

Replace the dates and times with the actual values.

```sh
curl -X GET "http://localhost:8080/topjava/rest/meals/filter?startDate=2024-07-01&startTime=08:00&endDate=2024-07-30&endTime=20:00" \
-H "Accept: application/json"
```