import axios from "axios";

import { Server } from "@modelcontextprotocol/sdk/server/index.js";

import { StdioServerTransport }
from "@modelcontextprotocol/sdk/server/stdio.js";

import {
  CallToolRequestSchema,
  ListToolsRequestSchema
} from "@modelcontextprotocol/sdk/types.js";

const server = new Server(
  {
    name: "alumnos-mcp",
    version: "1.0.0"
  },
  {
    capabilities: {
      tools: {}
    }
  }
);

//
// LIST TOOLS
//
server.setRequestHandler(
  ListToolsRequestSchema,
  async () => ({
    tools: [
      {
        name: "get_openapi",

        description:
          "Obtiene el OpenAPI del servicio alumnos",

        inputSchema: {
          type: "object",
          properties: {}
        }
      },

      {
        name: "get_alumno",

        description:
          "Obtiene un alumno por dni",

        inputSchema: {
          type: "object",

          properties: {
            dni: {
              type: "string"
            }
          },

          required: ["dni"]
        }
      },

      {
        name: "get_alumnos",

        description:
          "Obtiene todos los alumnos",

        inputSchema: {
          type: "object",
          properties: {}
        }
      }
    ]
  })
);

//
// CALL TOOL
//
server.setRequestHandler(
  CallToolRequestSchema,
  async (request) => {

    const tool = request.params.name;

    //
    // TOOL: get_openapi
    //
    if (tool === "get_openapi") {

      const response =
        await axios.get(
          "http://localhost:8080/v3/api-docs"
        );

      return {
        content: [
          {
            type: "text",
            text: JSON.stringify(response.data)
          }
        ]
      };
    }

    //
    // TOOL: get_alumno
    //
    if (tool === "get_alumno") {

      const dni =
        request.params.arguments.dni;

      const response =
        await axios.get(
          `http://localhost:8080/alumnos/${dni}`
        );

      return {
        content: [
          {
            type: "text",
            text: JSON.stringify(response.data)
          }
        ]
      };
    }

    //
    // TOOL: get_alumnos
    //
    if (tool === "get_alumnos") {

      const response =
        await axios.get(
          "http://localhost:8080/alumnos"
        );

      return {
        content: [
          {
            type: "text",
            text: JSON.stringify(response.data)
          }
        ]
      };
    }

    throw new Error(
      `Tool no encontrada: ${tool}`
    );
  }
);

const transport =
  new StdioServerTransport();

await server.connect(transport);