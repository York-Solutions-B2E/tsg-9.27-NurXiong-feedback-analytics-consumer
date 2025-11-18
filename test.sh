#!/bin/bash

# -----------------------------
# Configuration
# -----------------------------
IMAGE_NAME=feedback-consumer-builder
CONTAINER_NAME=feedback-consumer-test
KAFKA_HOST=kafka
KAFKA_PORT=9092
PROJECT_DIR=$(pwd)

# -----------------------------
# Detect Docker Compose default network
# -----------------------------
DEFAULT_NETWORK=feedbackapi_default

if [ -z "$DEFAULT_NETWORK" ]; then
    echo "❌ Docker network not found. Make sure Kafka is running via docker-compose."
    exit 1
fi

echo "Using Docker network: $DEFAULT_NETWORK"

# -----------------------------
# Build the builder stage of your Dockerfile
# -----------------------------
echo "Building builder stage of Dockerfile..."
docker build --target builder -t $IMAGE_NAME .

# -----------------------------
# Remove old test container if exists
# -----------------------------
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "Removing existing test container..."
    docker rm -f $CONTAINER_NAME
fi

# -----------------------------
# Run a temporary container for tests
# -----------------------------
echo "Starting test container..."
docker run -d --name $CONTAINER_NAME \
    --network $DEFAULT_NETWORK \
    -v "$PROJECT_DIR":/app \
    -w /app \
    $IMAGE_NAME \
    bash -c "sleep 1800"  # keep alive for 30 mins for debugging

# -----------------------------
# Wait until Kafka is ready
# -----------------------------
echo "Waiting for Kafka to be ready at $KAFKA_HOST:$KAFKA_PORT..."
until docker exec -i $CONTAINER_NAME bash -c "apt-get update >/dev/null && apt-get install -y netcat >/dev/null && nc -z $KAFKA_HOST $KAFKA_PORT"; do
    echo -n "."
    sleep 2
done
echo "✅ Kafka is ready!"

# -----------------------------
# Run consumer tests
# -----------------------------
echo "Running Kafka consumer tests..."
docker exec -it $CONTAINER_NAME mvn test \
    -DtrimStackTrace=false \
    -Dsurefire.redirectTestOutputToFile=false

# -----------------------------
# Optionally stop the test container after tests
# -----------------------------
echo "Cleaning up test container..."
docker rm -f $CONTAINER_NAME
