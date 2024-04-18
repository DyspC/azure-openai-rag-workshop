package ai.azure.openai.rag.workshop.backend.configuration;

import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;

import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;

import java.net.*;

import static java.time.Duration.ofSeconds;

public class ChatLanguageModelProducer {

  @ConfigProperty(name = "AZURE_OPENAI_KEY", defaultValue = "__dummy")
  String azureOpenAiKey;

  @ConfigProperty(name = "AZURE_OPENAI_URL")
  String azureOpenAiEndpoint;

  @ConfigProperty(name = "AZURE_OPENAI_DEPLOYMENT_NAME", defaultValue = "gpt-35-turbo")
  String azureOpenAiDeploymentName;

  @Produces
  public ChatLanguageModel chatLanguageModel() {
    return AzureOpenAiChatModel.builder()
      .apiKey(azureOpenAiKey)
      .endpoint(azureOpenAiEndpoint)
      .deploymentName(azureOpenAiDeploymentName)
      .timeout(ofSeconds(60))
      .logRequestsAndResponses(true)
      .build();
  }

}

