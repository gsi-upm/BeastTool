package es.upm.dit.gsi.beast.mock.common;

public class Definitions {

  //TODO por convenio las constantes deben ser en mayusculas.
    
    public static final String JADEX_LISTENER_MOCK_PATH = "es/upm/dit/gsi/beast/mock/jadex/listenerMock/ListenerMock.agent.xml";
    public static final String JADEX_BRIDGE_MOCK_PATH = "es/upm/dit/gsi/beast/mock/jadex/bridgeMock/BridgeMock.agent.xml";
    public static final String JADEX_REPOSITORY_MOCK_PATH = "es/upm/dit/gsi/beast/mock/jadex/repositoryMock/RepositoryMock.agent.xml";

    public static final String JADE_LISTENER_MOCK_PATH = "es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMockAgent";
    public static final String JADE_BRIDGE_MOCK_PATH = "es.upm.dit.gsi.beast.mock.jade.bridgeMock.BridgeMockAgent";
    public static final String JADE_DANNY_BRIDGE_MOCK_PATH = "es.upm.dit.gsi.beast.mock.jade.bridgeMock.DannyBridgeMockAgent";
    public static final String JADE_REPOSITORY_MOCK_PATH = "es.upm.dit.gsi.beast.mock.jade.repositoryMock.RepositoryMockAgent";

    public static final String LISTENER_SERVICE_TYPE = "mock-listener-agent";
    public static final String LISTENER_SERVICE_NAME = "listener-mock";
    public static final String LISTENER_AGENT_NAME = "ListenerMockAgent";
    
    public static final String BRIDGE_SERVICE_TYPE = "mock-messenger";
    public static final String BRIDGE_SERVICE_NAME = "bridge-mock";
    public static final String BRIDGE_AGENT_NAME = "BridgeMockAgent";

    public static final String REPOSITORY_SERVICE_TYPE = "mock-database-controller";
    public static final String REPOSITORY_SERVICE_NAME = "repository-mock";
    public static final String REPOSITORY_AGENT_NAME = "RepositoryMockAgent";
    public static final String STORE_ATTEMPT_OK = "The request has been properly proccessed";
    public static final String STORE_ATTEMPT_NOK = "There was an error trying to proccess the request";
    
    public static final String RECEIVED_MESSAGE_COUNT = "received_count";
    public static final String SENDED_MESSAGE_COUNT = "sended_count";
    public static final String STORED_DATA_COUNT = "stored_count";
    
    public static final int REG_ATTEMPTS = 3;

    
    
}
