package interfaces;

/**
 * Provedor centralizado de conexão: URL, usuário e senha.
 * Implementações podem ler de variáveis de ambiente, arquivo de config, etc.
 */
public interface IConnectionString {
    String getUrl();
    String getUser();
    String getPassword();
}
