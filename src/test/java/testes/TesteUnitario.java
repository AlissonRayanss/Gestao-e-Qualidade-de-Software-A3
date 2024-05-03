package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aplicacao.Programa;
import entidades.Cliente;
import entidades.GerenciarClientes;

public class TesteUnitario {
    private GerenciarClientes sistema;
    private Programa programa;
    
    @BeforeEach
    public void setUp() {
        sistema = mock(GerenciarClientes.class);
        programa = new Programa();
    }
    
    @Test
    public Cliente testCadastrarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        when(sistema.cadastrarCliente(any(Cliente.class))).thenReturn(true);
        
        assertTrue(GerenciarClientes.cadastrarCliente(sistema, cliente));
        
        verify(sistema, times(1)).cadastrarCliente(any(Cliente.class));
    }
    
    @Test
    public Cliente testEditarCliente() {
        Cliente clienteExistente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        Cliente clienteAtualizado = new Cliente("12345678900", "João da Silva", "Rua B", "987654321", "joao.silva@example.com");
        when(sistema.consultarCliente("12345678900")).thenReturn(clienteExistente);
        when(sistema.editarCliente(eq("12345678900"), any(Cliente.class))).thenReturn(true);
        
        assertTrue(GerenciarClientes.editarCliente(sistema, "12345678900", clienteAtualizado));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, times(1)).editarCliente(eq("12345678900"), any(Cliente.class));
    }
    
    @Test
    public Cliente testExcluirCliente() {
        when(sistema.excluirCliente("12345678900")).thenReturn(true);
        
        assertTrue(GerenciarClientes.excluirCliente(sistema, "12345678900"));
        
        verify(sistema, times(1)).excluirCliente("12345678900");
    }
    
    @Test
    public Cliente testConsultarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        when(sistema.consultarCliente("12345678900")).thenReturn(cliente);
        
        assertEquals(cliente, GerenciarClientes.consultarCliente(sistema, "12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
    }
    
    @Test
    public Cliente testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertEquals(clientes, GerenciarClientes.listarClientes(sistema));
        
        verify(sistema, times(1)).listarClientes();
    }
    
    @Test
    public Cliente testConsultarClienteInexistente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(null);
        
        assertNull(GerenciarClientes.consultarCliente(sistema, "12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
    }
    
    @Test
    public Cliente testEditarClienteInexistente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(null);
        
        assertFalse(GerenciarClientes.editarCliente(sistema, "12345678900", new Cliente()));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, never()).editarCliente(anyString(), any(Cliente.class));
    }
    
    @Test
    public Cliente testExcluirClienteInexistente() {
        when(sistema.excluirCliente("12345678900")).thenReturn(false);
        
        assertFalse(GerenciarClientes.excluirCliente(sistema, "12345678900"));
        
        verify(sistema, times(1)).excluirCliente("12345678900");
    }
    
    @Test
    public Cliente testListarClientesVazio() {
        List<Cliente> clientes = new ArrayList<>();
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertTrue(GerenciarClientes.listarClientes(sistema).isEmpty());
        
        verify(sistema, times(1)).listarClientes();
    }
}
