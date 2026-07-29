# Intervals ICU MCP

A Quarkus-based Model Context Protocol (MCP) server deployed on AWS Lambda that connects [Intervals.icu](https://intervals.icu) directly to Claude.ai Web Chat.

It eliminates the need to manually take screenshots, export CSVs, or copy-paste workout metrics into Claude for analysis.

## Features

- Exposes an MCP-compliant endpoint (`/mcp`) using the `quarkus-mcp-server-http` extension.
- Fetches, aggregates, and transforms ride data from the Intervals.icu API.
- Uses Vert.x route filtering to require authentication tokens, securing your AWS Lambda against unauthorized requests.
- Optimized for serverless deployment on AWS Lambda.

## Configuration

Set the following environment variables in your local environment or AWS Lambda configuration:

`INTERVALS_CLIENT_USERNAME` = `API_KEY`  
`INTERVALS_CLIENT_PASSWORD` = `your_intervals_api_key`  
`CLAUDE_AUTH_TOKEN` = `your_secret_bearer_token`  

## Build & Deploy

Build the Quarkus application package:

```bash
./mvnw clean package
```

Deploy the generated deployment package to AWS Lambda and enable a Lambda Function URL (or front it with API Gateway).

## Connecting to Claude.ai

1. Open Claude.ai and navigate to Settings > Custom Connectors.

2. Click Add new connector.

3. Set the connector URL to your Lambda Function URL, passing your security token: `https://<your-lambda-id>.lambda-url.<region>.on.aws/mcp?token=your_secret_bearer_token`

4. Save the connector.

## Usage

In your Claude Web Chat session, ask:

"Get my latest ride data and analyze it."

Claude will automatically invoke the underlying tool, fetch your workout metrics from Intervals.icu, and generate the analysis.
