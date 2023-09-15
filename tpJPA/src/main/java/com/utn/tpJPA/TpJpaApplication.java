package com.utn.tpJPA;

import com.utn.tpJPA.entidades.*;
import com.utn.tpJPA.enumeraciones.Estado;
import com.utn.tpJPA.enumeraciones.FormaPago;
import com.utn.tpJPA.enumeraciones.Tipo;
import com.utn.tpJPA.enumeraciones.TipoEnvio;
import com.utn.tpJPA.repositorios.ClienteRepositorio;
import com.utn.tpJPA.repositorios.RubroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class TpJpaApplication {
	@Autowired
	ClienteRepositorio clienteRepositorio;

	@Autowired
	RubroRepositorio rubroRepositorio;

	public static void main(String[] args) {

		SpringApplication.run(TpJpaApplication.class, args);
		System.out.println("Estoy funcionando hoy");
	}

	@Bean
	CommandLineRunner init(ClienteRepositorio clienteRepositorio) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			//1.Crear rubros

			Rubro rubro1 = Rubro.builder()
					.denominacion("denom")
					.build();
			//2.Crear producto
			Producto producto1 =Producto.builder()
					.tiempoEstimadoCocina(20)
					.denominacion("pizza especial")
					.precioVenta(1700.0)
					.precioCompra(900.0)
					.stockActual(12)
					.stockMin(6)
					.unidadMedida("unidad1")
					.receta("blablablablablabla")
					.tipo(Tipo.insumo)
					.build();

			Producto producto2 =Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("hamburguesa completa")
					.precioVenta(1555.9)
					.precioCompra(1000.0)
					.stockActual(30)
					.stockMin(2)
					.unidadMedida("unidad2")
					.receta("blablablablablabla")
					.tipo(Tipo.insumo)
					.build();

			//3.Asocio RUBRO-PRODUCTO
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			//guardo
			rubroRepositorio.save(rubro1);
			rubroRepositorio.save(rubro1);

			//4.Crear instancia de cliente y agregar domicilios
			Domicilio domicilio1 = Domicilio.builder()
			.calle("Lamadrid")
			.nro("6666")
			.localidad("Ciudad")
			.build();

			Domicilio domicilio2 = Domicilio.builder()
			.calle("Boedo")
			.nro("4444")
			.localidad("Lujan de cuyo")
			.build();


			Cliente cliente = Cliente.builder()
			.nombre("Ramiro")
			.apellido("Díaz")
			.email("RamiroDiaz@gmail.com")
			.telefono("26156667789")
			.build();


			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);




			//Creo Pedido y asocio a cliente
			//configuracion de la fecha
			SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-mm-dd");
			String fechaString= "2021-09-17";
			Date fecha=formatoFecha.parse(fechaString);

			Pedido pedido1=Pedido.builder()
					.fecha(fecha)
					.estado(Estado.iniciado)
					.tipoEnvio(TipoEnvio.retiro)
					.totalP(2100.0)
					.build();

			Pedido pedido2=Pedido.builder()
					.fecha(fecha)
					.estado(Estado.preparando)
					.tipoEnvio(TipoEnvio.delivery)
					.totalP(3100.0)
					.build();

			cliente.agregarPedidos(pedido1);
			cliente.agregarPedidos(pedido2);

			//Crear detalle pedido y asociar a pedido
			DetallePedido detallePedido1= DetallePedido.builder()
					.cantidad(1)
					.subtotal(2100)
					.build();
			DetallePedido detallePedido2= DetallePedido.builder()
					.cantidad(2)
					.subtotal(3100)
					.build();
			pedido1.agregarDetallePedido(detallePedido1);
			pedido2.agregarDetallePedido(detallePedido2);

			//vincular detalle pedido con producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);

			//crear factura y relacionar con pedido
			Factura factura1= Factura.builder()
					.fecha(fecha)
					.total(4500)
					.numero(345)
					.descuento(0)
					.formaPago(FormaPago.efectivo)
					.build();
			Factura factura2= Factura.builder()
					.fecha(fecha)
					.total(6900)
					.numero(5589)
					.descuento(1000)
					.formaPago(FormaPago.tarjeta)
					.build();
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			//guardar datos
			clienteRepositorio.save(cliente);
			rubroRepositorio.save(rubro1);

			// Recuperar el objetos desde la base de datos

			Cliente clienteRecuperado = clienteRepositorio.findById(cliente.getId()).orElse(null);


			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();

			}
			Rubro rubroRecuperado = rubroRepositorio.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}

		};

	}

}


