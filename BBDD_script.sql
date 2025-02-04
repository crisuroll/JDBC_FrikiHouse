CREATE SCHEMA IF NOT EXISTS `FrikiHouse`;

USE `FrikiHouse`;

CREATE TABLE IF NOT EXISTS `Series` (
	`id_serie` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    PRIMARY KEY(`id_serie`)
);

CREATE TABLE IF NOT EXISTS `Productos` (
	`id_producto` INT NOT NULL AUTO_INCREMENT,
    `id_serie` INT NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `marca` VARCHAR(60) NOT NULL,
    `material` VARCHAR(50) NOT NULL,
    `precio` DECIMAL NOT NULL,
    `stock` INT NOT NULL,
    PRIMARY KEY(`id_producto`),
    CONSTRAINT fk_productos_serie FOREIGN KEY (`id_serie`) REFERENCES `Series`(`id_serie`)
);

CREATE TABLE IF NOT EXISTS `Proveedores` (
	`id_proveedor` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(70) NOT NULL,
    `contacto` VARCHAR(100) NOT NULL,
    PRIMARY KEY(`id_proveedor`)
);

CREATE TABLE IF NOT EXISTS `Pedidos` (
	`id_pedido` INT NOT NULL AUTO_INCREMENT,
    `id_proveedor` INT NOT NULL,
    `fecha_pedido` DATETIME NOT NULL,
    `cantidad_total` INT NOT NULL,
    PRIMARY KEY(`id_pedido`),
    CONSTRAINT fk_pedidos_proveedores FOREIGN KEY (`id_proveedor`) REFERENCES `Proveedores`(`id_proveedor`)
);

CREATE TABLE IF NOT EXISTS `Clientes` (
	`dni` VARCHAR(9) NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `apellido1` VARCHAR(50) NOT NULL,
    `apellido2` VARCHAR(50),
    PRIMARY KEY(`dni`)
);

CREATE TABLE IF NOT EXISTS `Ventas` (
	`id_venta` INT NOT NULL AUTO_INCREMENT,
    `dni` VARCHAR(9) NOT NULL,
    `fecha_venta` DATETIME NOT NULL,
    `subtotal` DECIMAL NOT NULL,
    PRIMARY KEY(`id_venta`),
    CONSTRAINT fk_ventas_clientes FOREIGN KEY (`dni`) REFERENCES `Clientes`(`dni`)
);

CREATE TABLE IF NOT EXISTS `Detalle_Pedido` (
	`id_detalle_pedido` INT NOT NULL AUTO_INCREMENT,
    `id_pedido` INT NOT NULL,
    `id_producto` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `subtotal` DECIMAL NOT NULL,
    PRIMARY KEY(`id_detalle_pedido`),
    CONSTRAINT fk_detallepedido_pedidos FOREIGN KEY (`id_pedido`) REFERENCES `Pedidos`(`id_pedido`),
    CONSTRAINT fk_detallepedido_productos FOREIGN KEY (`id_producto`) REFERENCES `Productos`(`id_producto`)
);

CREATE TABLE IF NOT EXISTS `Detalle_Venta` (
	`id_detalle_venta` INT NOT NULL AUTO_INCREMENT,
    `id_venta` INT NOT NULL,
    `id_producto` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `subtotal` DECIMAL NOT NULL,
    PRIMARY KEY(`id_detalle_venta`),
    CONSTRAINT fk_detalleventa_ventas FOREIGN KEY (`id_venta`) REFERENCES `Ventas`(`id_venta`),
    CONSTRAINT fk_detalleventa_productos FOREIGN KEY (`id_producto`) REFERENCES `Productos`(`id_producto`)
);

CREATE TABLE IF NOT EXISTS `Usuarios` (
	`id_usuario` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `contraseña` VARCHAR(50) NOT NULL,
    `rol` ENUM('admin', 'empleado') NOT NULL,
    PRIMARY KEY(`id_usuario`)
);

CREATE TABLE IF NOT EXISTS `Garantia` (
	`id_garantia` INT NOT NULL,
    `id_venta` INT NOT NULL,
    `fecha_inicio` DATETIME NOT NULL,
    `fecha_fin` DATETIME NOT NULL,
    PRIMARY KEY(`id_garantia`),
    CONSTRAINT fk_garantia_ventas FOREIGN KEY (`id_venta`) REFERENCES `Ventas`(`id_venta`)
);