package services;

import GlobalExceptionHandler.ContaInvalidaException;
import GlobalExceptionHandler.SaldoInsuficienteException;
import models.ContaBancaria;
import models.ContaCorrente;

public interface ContaService {
    ContaCorrente getContaPorNumero (String numeroConta);
    void depositar(String numeroConta, double valor) throws ContaInvalidaException;
    void sacar (String numeroConta, double valor) throws ContaInvalidaException, SaldoInsuficienteException;
    void transferir(String contaOrigem, String contaDestino, double valorTransferencia) throws ContaInvalidaException, SaldoInsuficienteException;
    ContaCorrente criarConta (String nome, String cpf) throws ContaInvalidaException;

}
