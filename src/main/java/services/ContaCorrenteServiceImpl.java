package services;

import GlobalExceptionHandler.ContaInvalidaException;
import GlobalExceptionHandler.SaldoInsuficienteException;
import models.ContaBancaria;
import models.ContaCorrente;
import models.Cliente;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ContaCorrenteServiceImpl implements ContaService{
    private final List<ContaCorrente> contasCorrentes;
    private static final AtomicInteger contadorContas = new AtomicInteger(1);

    public ContaCorrenteServiceImpl(List<ContaCorrente> contasCorrentes) {
        this.contasCorrentes = contasCorrentes;
    }

    @Override
    public ContaCorrente getContaPorNumero(String numeroConta) {
        return contasCorrentes.stream()
                .filter(conta -> conta.getNumeroConta().equals(numeroConta))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void depositar(String numeroConta, double valor) throws ContaInvalidaException {
        ContaCorrente conta = getContaPorNumero(numeroConta);
        if(conta == null){
            throw new ContaInvalidaException("Conta Inválida");
        }
        conta.depositar(valor);
    }

    @Override
    public void sacar(String numeroConta, double valorSaque) throws ContaInvalidaException, SaldoInsuficienteException {
        ContaCorrente conta = getContaPorNumero(numeroConta);
        if(conta != null) {
            if(conta.getSaldo() >= valorSaque){
                conta.sacar(valorSaque);
            } else {
                throw new SaldoInsuficienteException("Saldo Insuficiente");
            }
        } else {
            throw new ContaInvalidaException("Conta Inválida");
        }
    }

    @Override
    public void transferir(String contaOrigem, String contaDestino, double valorTransferencia) throws ContaInvalidaException, SaldoInsuficienteException {
        ContaBancaria cOrigem = getContaPorNumero(contaOrigem);
        ContaBancaria cDestino = getContaPorNumero(contaDestino);

        if(cOrigem == null){
            throw new ContaInvalidaException("Conta de origem não encontrada!");
        }
        if(cDestino == null){
            throw new ContaInvalidaException("Conta de destino não encontrada");
        }

        System.out.println("Conta de origem: " + cOrigem.getNumeroConta() +"\nConta de Destino: " + cDestino.getNumeroConta());

        if(cOrigem.getSaldo() >= valorTransferencia){
            cOrigem.transferir(cOrigem, cDestino, valorTransferencia);
        } else {
            throw new SaldoInsuficienteException("Saldo da conta de origem insuficiente para realizar a transferência");
        }
    }

    @Override
    public ContaCorrente criarConta(String nome, String cpf) throws ContaInvalidaException {
        if(nome.isEmpty() || cpf.isEmpty()){
            throw new ContaInvalidaException("Os campos de Nome e CPF são obrigatórios.");
        }

        String numeroConta = gerarNumeroConta();

        Cliente novoCliente = new Cliente (nome, cpf);

        ContaCorrente novaContaCorrente = new ContaCorrente(numeroConta, 0.0, novoCliente);
        this.contasCorrentes.add(novaContaCorrente);

        return novaContaCorrente;
    }

    private String gerarNumeroConta() {
        return String.format("%05d", contadorContas.getAndIncrement());
    }
}
