package Controlador;


import Modelo.CasoSoporte;
import Modelo.ImageUtils;
import Modelo.Producto;
import Vista.PanelCasoSoporte;
import Vista.PanelConsultaProducto;
import Vista.PanelConsultarCasoSoporte;
import Vista.PanelProducto;
import Vista.PanelProductoAgregar;
import Vista.PanelRegistrarCasoSoporte;
import Vista.PanelRegistrarOrdenCompra;
import Vista.PanelRegistrarProducto;
import Vista.PantallaModificarCasoSoporte;
import Vista.PantallaModificarProducto;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
public class Controlador {
    PanelRegistrarProducto panel1;
    PanelConsultaProducto panel2;
    PanelProducto panel3;
    PantallaModificarProducto pantalla1;
    PanelRegistrarCasoSoporte panel4;
    PanelCasoSoporte panel5;
    PantallaModificarCasoSoporte pantalla2;
    PanelConsultarCasoSoporte panel6;
    PanelRegistrarOrdenCompra panel7;
    PanelProductoAgregar panel8;
            
    public Controlador() {
    }

    public Controlador(PanelRegistrarProducto panel1) {
        this.panel1 = panel1;
    }

    public Controlador(PanelConsultaProducto panel2) {
        this.panel2 = panel2;
    }

    public Controlador(PanelProducto panel3) {
        this.panel3 = panel3;
    }

    public Controlador(PantallaModificarProducto pantalla1) {
        this.pantalla1 = pantalla1;
    }

    public Controlador(PanelRegistrarCasoSoporte panel4) {
        this.panel4 = panel4;
    }

    public Controlador(PantallaModificarCasoSoporte pantalla2) {
        this.pantalla2 = pantalla2;
    }

    public Controlador(PanelCasoSoporte panel5) {
        this.panel5 = panel5;
    }

    public Controlador(PanelConsultarCasoSoporte panel6) {
        this.panel6 = panel6;
    }

    public Controlador(PanelRegistrarOrdenCompra panel7) {
        this.panel7 = panel7;
    }

    public Controlador(PanelProductoAgregar panel8) {
        this.panel8 = panel8;
    }
    
    //Metodos de validacion
    private boolean verificarFecha(com.toedter.calendar.JDateChooser dateChooser) {
        return dateChooser.getDate() != null;
    }
    
    public boolean verificarCampo(JTextField jTextField) {
        String texto = jTextField.getText().trim(); // Elimina espacios al principio y al final
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este campo no puede estar vacío.");
            return false;
        }
        jTextField.setText(texto); // Actualiza el JTextField con el texto limpio
        return true;
    }
    
    public boolean verificarCampoNumerico(JTextField jTextField) {
        String texto = jTextField.getText().trim();
        try {
            // Intentamos parsear el texto como un número
            Double.parseDouble(texto);
            jTextField.setText(texto); // Actualiza el JTextField con el texto limpio
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo debe contener solo números.");
            return false;
        }
    }
    
    public boolean verificarTextArea(JTextArea jTextArea) {
        String texto = jTextArea.getText().trim(); // Elimina espacios al principio y al final
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de descripción no puede estar vacío.");
            return false;
        }
        jTextArea.setText(texto); // Actualiza el JTextArea con el texto limpio
        return true;
    }
    //--
    
    
    //Metodos para registrar un producto
    public void registrarProducto(){
        try {
            // Verificar que todos los campos sean válidos (puedes implementar estas funciones de validación)
            if (verificarCampo(panel1.getjTextField1()) && verificarTextArea(panel1.getjTextArea1()) && verificarCampoNumerico(panel1.getjTextField3()) && verificarCampoNumerico(panel1.getjTextField6()) && verificarCampo(panel1.getjTextField4()) && verificarCampo(panel1.getjTextField5())) {               
                // Verifica que se haya seleccionado una imagen
                if (panel1.getImagenSeleccionada() == null) {
                    JOptionPane.showMessageDialog(panel1, "Por favor selecciona una imagen antes de registrar el producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Redimensionar la imagen seleccionada y convertirla a byte[]
                byte[] imagenBytes = ImageUtils.resizeImageToBytes(panel1.getImagenSeleccionada(), 200, 200); // Redimensiona a 200x200 píxeles
                // Crear el objeto Producto y asignar los datos
                Producto nuevoProducto = new Producto();
                nuevoProducto.setNombre_producto(panel1.getjTextField1().getText());
                nuevoProducto.setDescripcion(panel1.getjTextArea1().getText());
                nuevoProducto.setCategoria(panel1.getjTextField4().getText());
                nuevoProducto.setMarca(panel1.getjTextField5().getText());
                nuevoProducto.setPrecio(Integer.parseInt(panel1.getjTextField3().getText()));
                nuevoProducto.setCantidad(Integer.parseInt(panel1.getjTextField6().getText()));
                nuevoProducto.setImagen(imagenBytes); // Guardar la imagen como byte[] en la base de datos
                // Llamar al método para guardar el producto en la base de datos
                nuevoProducto.guardarProducto();
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(panel1, "Producto registrado exitosamente.");
            }
            } catch (IOException ex) {
                // Manejo de errores de procesamiento de la imagen
                JOptionPane.showMessageDialog(panel1, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void cargarImagenregistrar() {
        // Abre un JFileChooser para seleccionar una imagen
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Selecciona una imagen");
    int result = fileChooser.showOpenDialog(panel1);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        // Obtener el archivo seleccionado
        File imagenSeleccionada = panel1.getImagenSeleccionada();
        imagenSeleccionada = fileChooser.getSelectedFile();
        
        try {
            // Redimensionar la imagen seleccionada
            BufferedImage resizedImage = ImageUtils.resizeImage(imagenSeleccionada, 200, 200); // Tamaño de 200x200
            // Mostrar la imagen redimensionada en el JLabel
            panel1.getjLabel8().setIcon(new javax.swing.ImageIcon(resizedImage));
            panel1.setImagenSeleccionada(imagenSeleccionada);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel1, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    //--
    
    //Metodos de consulta de productos
    public void cargarProducto(){
        // Obtener la lista de productos desde la base de datos
        List<Producto> productos = Producto.listarProductos();
    
        // Configuramos el layout para mostrar los productos
        panel2.getjPanel1().setLayout(new GridLayout(0, 1, 10, 10)); // Ajusta el layout a GridLayout si quieres una columna vertical
    
        // Recorrer los productos y agregar los paneles correspondientes
        for (Producto producto : productos) {
            PanelProducto panelProducto = new PanelProducto();
            panelProducto.setjPanelPadre(panel2.getjPanel1());  // Establecemos el panel padre para que el producto se agregue allí
            Controlador controlador = new Controlador(panelProducto);
            controlador.cargarProductoRegistrar(producto); // Cargamos los datos del producto en el panel
            panel2.getjPanel1().add(panelProducto);  // Agregamos el panel de producto al jPanel1
        }
    
        panel2.getjPanel1().revalidate();  // Refrescar la interfaz
        panel2.getjPanel1().repaint();
        
    }
    
    public void filtrarProductos() {
    String texto = panel2.getjTextField1().getText().toLowerCase();
    panel2.getjPanel1().removeAll(); // Limpiar el panel

    // Obtener los productos filtrados desde la base de datos
    List<Producto> productosFiltrados = Producto.listarProductosFiltrados(texto);

    // Recorrer los productos filtrados y agregar los paneles correspondientes
    for (Producto producto : productosFiltrados) {
        PanelProducto panelProducto = new PanelProducto();
        panelProducto.setjPanelPadre(panel2.getjPanel1());  // Establecemos el panel padre
        Controlador controlador = new Controlador(panelProducto);
        controlador.cargarProductoRegistrar(producto); // Cargamos los datos del producto
        panel2.getjPanel1().add(panelProducto);  // Agregamos el panel de producto al jPanel1
    }

    panel2.getjPanel1().revalidate();  // Refrescar la interfaz
    panel2.getjPanel1().repaint();
}
    //--

    //Metodos para un producto invidual
    public void cargarProductoRegistrar(Producto producto){
        // Cargar los datos del producto en los labels
        panel3.getjLabel8().setText(producto.getNombre_producto());
        panel3.getjLabel10().setText(producto.getDescripcion());
        panel3.getjLabel9().setText(Integer.toString(producto.getPrecio()));
        panel3.getjLabel11().setText(producto.getCategoria());
        panel3.getjLabel7().setText(producto.getMarca());
        panel3.getjLabel13().setText(Integer.toString(producto.getCantidad()));  // Aquí cargamos la cantidad

        // Cargar la imagen del producto (asegurándote de que el método getImagen() devuelva una ruta válida o una imagen en formato adecuado)
        // jLabel14 es el JLabel donde se mostrará la imagen
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenProducto = new ImageIcon(imagenBytes);
            panel3.getjLabel14().setIcon(imagenProducto);
        }
    
        panel3.setId(producto.getId());
        
    }
    
    public void abrirVentanaEdicionProducto() throws IOException{
        // Abre la ventana
        PantallaModificarProducto pantalla = new PantallaModificarProducto();
        pantalla.setPantalla(panel3);
        // Crear el objeto Producto con los datos actuales del producto en la vista
        Producto producto = new Producto(
            panel3.getId(),
            panel3.getjLabel8().getText(),  // Nombre del producto
            panel3.getjLabel10().getText(), // Descripción del producto
            panel3.getjLabel11().getText(),  // Categoría del producto
            panel3.getjLabel7().getText(),  // Marca del producto
            Integer.parseInt(panel3.getjLabel9().getText()), // Precio del producto           
            Integer.parseInt(panel3.getjLabel13().getText()), // Cantidad del producto
            obtenerImagenBytes() // Obtén los bytes de la imagen actual
        );
    
        // Cargar los datos del producto en la ventana de modificación
        Controlador controlador = new Controlador(pantalla1);
        controlador.cargarProductoModificar(producto);
    
        // Mostrar la ventana
        pantalla.setVisible(true);
    }
    
    public byte[] obtenerImagenBytes() {
    ImageIcon imagenIcon = (ImageIcon) panel3.getjLabel14().getIcon(); // Obtiene la imagen del JLabel

    if (imagenIcon != null) {
        Image image = imagenIcon.getImage(); // Obtén la imagen como Image

        // Crear un BufferedImage compatible
        BufferedImage bufferedImage = new BufferedImage(
            image.getWidth(null),
            image.getHeight(null),
            BufferedImage.TYPE_INT_RGB
        );

        // Dibujar la imagen original en el BufferedImage
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Convertir el BufferedImage a byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);  // Cambia "jpg" según el formato deseado
            baos.flush();
            return baos.toByteArray();  // Retorna el byte[] de la imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return null;  // Si no hay imagen, devuelve null
}

    public void eliminarProducto(){
        // Crear un objeto Producto con el id del producto que queremos eliminar
    Producto producto = new Producto();
    producto.setId(panel3.getId());

    // Eliminar el producto de la base de datos
    producto.eliminarProducto();

    // Eliminar el panel del producto de la vista principal (jPanelPadre)
    panel3.getjPanelPadre().remove(panel3);
    panel3.getjPanelPadre().revalidate();
    panel3.getjPanelPadre().repaint();

    // Mostrar un mensaje de éxito (opcional)
    JOptionPane.showMessageDialog(panel3, "Producto eliminado exitosamente");
    }
    //--
    
    //Metodos para modificar un producto
    public void cargarImagenmodificar() {
        // Abre un JFileChooser para seleccionar una imagen
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Selecciona una imagen");
    int result = fileChooser.showOpenDialog(pantalla1);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        // Obtener el archivo seleccionado
        File imagenSeleccionada = pantalla1.getImagenSeleccionada();
        imagenSeleccionada = fileChooser.getSelectedFile();
        
        try {
            // Redimensionar la imagen seleccionada
            BufferedImage resizedImage = ImageUtils.resizeImage(imagenSeleccionada, 200, 200); // Tamaño de 200x200
            // Mostrar la imagen redimensionada en el JLabel
            pantalla1.getjLabel8().setIcon(new javax.swing.ImageIcon(resizedImage));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(pantalla1, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    //ira al controlador
    public void cargarProductoModificar(Producto producto) throws IOException{
        pantalla1.getjTextField1().setText(producto.getNombre_producto());
        pantalla1.getjTextArea1().setText(producto.getDescripcion());
        pantalla1.getjTextField3().setText(Integer.toString(producto.getPrecio()));
        pantalla1.getjTextField6().setText(Integer.toString(producto.getCantidad()));
        pantalla1.getjTextField4().setText(producto.getCategoria());
        pantalla1.getjTextField5().setText(producto.getMarca());
        pantalla1.setId(producto.getId());
        // Cargar la imagen si existe
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenIcon = new ImageIcon(imagenBytes);
            pantalla1.getjLabel8().setIcon(imagenIcon);  // Asegúrate de tener un JLabel para mostrar la imagen 
            // Guardar imagen actual en imagenSeleccionada
            pantalla1.setImagenSeleccionada(ImageUtils.bytesToFile(imagenBytes, "imagen_actual.jpg"));
        }
    }
    
    public void guardarCambiosProducto() {
    try {
        // Obtener los valores ingresados en los campos
        String nombre = pantalla1.getjTextField1().getText().trim();
        String descripcion = pantalla1.getjTextArea1().getText().trim();
        String categoria = pantalla1.getjTextField4().getText().trim();
        String marca = pantalla1.getjTextField5().getText().trim();
        int precio = Integer.parseInt(pantalla1.getjTextField3().getText().trim());
        int cantidad = Integer.parseInt(pantalla1.getjTextField6().getText().trim());

        // Validar que no haya campos vacíos
        if (nombre.isEmpty() || descripcion.isEmpty() || categoria.isEmpty() || marca.isEmpty()) {
            JOptionPane.showMessageDialog(pantalla1, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Producto con los nuevos valores
        Producto producto = new Producto();
        producto.setId(pantalla1.getId());
        producto.setNombre_producto(nombre);
        producto.setDescripcion(descripcion);
        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);

        // Validar que haya una imagen seleccionada o cargada
        if (pantalla1.getImagenSeleccionada() == null) {
            JOptionPane.showMessageDialog(pantalla1, "Por favor, seleccione o cargue una imagen del producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir la imagen seleccionada a byte[]
        byte[] imagenBytes = ImageUtils.resizeImageToBytes(pantalla1.getImagenSeleccionada(), 200, 200);
        producto.setImagen(imagenBytes);


        // Llamar al método de actualización en la clase Producto

        producto.actualizarProducto();
        Controlador controlador = new Controlador(pantalla1.getPantalla());
        controlador.cargarProductoRegistrar(producto);
        // Notificar éxito
        JOptionPane.showMessageDialog(pantalla1, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Ejecutar el callback si existe
        /*if (onProductoEditado != null) {
            onProductoEditado.run();
        }*/

        // Cerrar la ventana
        pantalla1.dispose();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(pantalla1, "Ingrese valores numéricos válidos para el precio y la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(pantalla1, "Ocurrió un error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    //--
    //Metodos para los casos de soporte
    public void registrarCasosoporte(){
        try {
            // Verificar que todos los campos sean válidos
            if (verificarCampo(panel4.getjTextField1()) && verificarTextArea(panel4.getjTextArea1()) && verificarFecha(panel4.getjDateChooser1())) {               
                // Crear el objeto CasoSoporte y asignar los datos
                CasoSoporte nuevoCaso = new CasoSoporte();
                nuevoCaso.setNombre_cliente(panel4.getjTextField1().getText());
                nuevoCaso.setDescripcion(panel4.getjTextArea1().getText());
                
                nuevoCaso.setFecha_registro(panel4.getjDateChooser1().getDate());
                nuevoCaso.setEstado(panel4.getjComboBox1().getSelectedItem().toString());
                
                // Aquí puedes guardar el caso en la base de datos
                nuevoCaso.guardarCaso();
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(panel4, "Caso de soporte registrado exitosamente.");
                panel4.limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(panel4, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(panel4, "Error al registrar el caso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Metodos para un caso de soporte invidual
    public void cargarCasoSoporteRegistrar(CasoSoporte casosoporte){
        // Cargar los datos del producto en los labels
        panel5.getjLabel8().setText(casosoporte.getNombre_cliente());
        panel5.getjLabel10().setText(casosoporte.getDescripcion());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss");
;       panel5.getjLabel9().setText(formatter.format(casosoporte.getFecha_registro()));
        panel5.getjLabel13().setText(casosoporte.getEstado());
        panel5.setId(casosoporte.getId());
        
    }
    
    public void abrirVentanaEdicionCasoSoporte() throws IOException, ParseException{
        // Abre la ventana
        PantallaModificarCasoSoporte pantalla = new PantallaModificarCasoSoporte();
        pantalla.setPantalla(panel5);
        // Crear el objeto Producto con los datos actuales del producto en la vista
        //
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = formato.parse(panel5.getjLabel9().getText());
        CasoSoporte casosoporte = new CasoSoporte(
            panel5.getId(),
            panel5.getjLabel8().getText(),  // Nombre del cliente
            panel5.getjLabel10().getText(), (Timestamp) fecha,  // Fecha de registro
            panel5.getjLabel13().getText()  // Estado del caso

        );
    
        // Cargar los datos del producto en la ventana de modificación
        Controlador controlador = new Controlador(pantalla2);
        controlador.cargarCasoSoporteModificar(casosoporte);
    
        // Mostrar la ventana
        pantalla.setVisible(true);
    }

    public void eliminarCasoSoporte(){
        // Crear un objeto Producto con el id del producto que queremos eliminar
    CasoSoporte casosoporte = new CasoSoporte();
    casosoporte.setId(panel5.getId());

    // Eliminar el producto de la base de datos
    casosoporte.eliminarCaso();

    // Eliminar el panel del producto de la vista principal (jPanelPadre)
    panel5.getjPanelPadre().remove(panel5);
    panel5.getjPanelPadre().revalidate();
    panel5.getjPanelPadre().repaint();

    // Mostrar un mensaje de éxito (opcional)
    JOptionPane.showMessageDialog(panel5, "Producto eliminado exitosamente");
    }
    //--
    
    //Metodos para modificar:
    public void cargarCasoSoporteModificar(CasoSoporte casosoporte) throws IOException{
        pantalla2.getjTextField1().setText(casosoporte.getNombre_cliente());
        pantalla2.getjTextArea1().setText(casosoporte.getDescripcion()); 
        pantalla2.getjDateChooser1().setDate(casosoporte.getFecha_registro());
        pantalla2.getjComboBox1().setSelectedItem(casosoporte.getEstado());
        pantalla2.setId(casosoporte.getId());
        
    }
    
    public void guardarCambiosCasoSoporte() {
    try {
        // Obtener los valores ingresados en los campos
        String nombre_cliente = pantalla2.getjTextField1().getText().trim();
        String descripcion = pantalla2.getjTextArea1().getText().trim();
        Date fecha_registro = pantalla2.getjDateChooser1().getDate();
        String estado = pantalla2.getjComboBox1().getSelectedItem().toString();
        
        // Validar que no haya campos vacíos
        if (nombre_cliente.isEmpty() || descripcion.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(pantalla2, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Producto con los nuevos valores
        CasoSoporte casosoporte = new CasoSoporte();
        casosoporte.setId(pantalla2.getId());
        casosoporte.setNombre_cliente(nombre_cliente);
        casosoporte.setDescripcion(descripcion);
        casosoporte.setFecha_registro(fecha_registro);
        casosoporte.setEstado(estado);
        
        // Llamar al método de actualización en la clase Producto
        casosoporte.actualizarCaso();
        Controlador controlador = new Controlador(pantalla2.getPantalla());
        controlador.cargarCasoSoporteRegistrar(casosoporte);
        // Notificar éxito
        JOptionPane.showMessageDialog(pantalla2, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Ejecutar el callback si existe
        /*if (onProductoEditado != null) {
            onProductoEditado.run();
        }*/

        // Cerrar la ventana
        pantalla2.dispose();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(pantalla2, "Ingrese valores numéricos válidos para el precio y la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(pantalla2, "Ocurrió un error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    //Metodos de consulta de casos de soporte
    public void cargarCasoSoporte(){
        // Obtener la lista de productos desde la base de datos
        List<CasoSoporte> casos = CasoSoporte.listarCasos();
    
        // Configuramos el layout para mostrar los productos
        panel6.getjPanel1().setLayout(new GridLayout(0, 1, 10, 10)); // Ajusta el layout a GridLayout si quieres una columna vertical
    
        // Recorrer los productos y agregar los paneles correspondientes
        for (CasoSoporte caso : casos) {
            PanelCasoSoporte panelCasoSoporte = new PanelCasoSoporte();
            panelCasoSoporte.setjPanelPadre(panel6.getjPanel1());  // Establecemos el panel padre para que el producto se agregue allí
            Controlador controlador = new Controlador(panelCasoSoporte);
            controlador.cargarCasoSoporteRegistrar(caso); // Cargamos los datos del producto en el panel
            panel6.getjPanel1().add(panelCasoSoporte);  // Agregamos el panel de producto al jPanel1
        }
    
        panel6.getjPanel1().revalidate();  // Refrescar la interfaz
        panel6.getjPanel1().repaint();
        
    }
    
    public void filtrarCasosSoporte() {
    String texto = panel6.getjTextField1().getText().toLowerCase();
    panel6.getjPanel1().removeAll(); // Limpiar el panel

    // Obtener los productos filtrados desde la base de datos
    List<CasoSoporte> casosFiltrados = CasoSoporte.listarCasosFiltrados(texto);

    // Recorrer los productos filtrados y agregar los paneles correspondientes
    for (CasoSoporte caso : casosFiltrados) {
        PanelCasoSoporte panelCasoSoporte = new PanelCasoSoporte();
        panelCasoSoporte.setjPanelPadre(panel6.getjPanel1());  // Establecemos el panel padre
        Controlador controlador = new Controlador(panelCasoSoporte);
        controlador.cargarCasoSoporteRegistrar(caso); // Cargamos los datos del producto
        panel6.getjPanel1().add(panelCasoSoporte);  // Agregamos el panel de producto al jPanel1
    }

    panel6.getjPanel1().revalidate();  // Refrescar la interfaz
    panel6.getjPanel1().repaint();
}
    //--
    //Metodos para agregar una orden de compra
    
      //Metodos para un producto invidual dentro de la otden de compta
    public void cargarProductoAgregarOrdenCompra(Producto producto){
        // Cargar los datos del producto en los labels
        panel8.getjLabel8().setText(producto.getNombre_producto());
        panel8.getjLabel10().setText(producto.getDescripcion());
        panel8.getjLabel9().setText(Integer.toString(producto.getPrecio()));
        panel8.getjLabel11().setText(producto.getCategoria());
        panel8.getjLabel7().setText(producto.getMarca());
        panel8.getjLabel13().setText(Integer.toString(producto.getCantidad()));  // Aquí cargamos la cantidad
        panel8.setCantidadtotal(producto.getCantidad());
        // Cargar la imagen del producto (asegurándote de que el método getImagen() devuelva una ruta válida o una imagen en formato adecuado)
        // jLabel14 es el JLabel donde se mostrará la imagen
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenProducto = new ImageIcon(imagenBytes);
            panel8.getjLabel14().setIcon(imagenProducto);
        }
    
        panel8.setId(producto.getId());
       
    }
    
    //Metodo complementario
    
    
    public void cargarProductoOrdenCompra(){
        // Obtener la lista de productos desde la base de datos
        List<Producto> productos = Producto.listarProductos();
    
        // Configuramos el layout para mostrar los productos
        panel7.getjPanel2().setLayout(new GridLayout(0, 1, 10, 10)); // Ajusta el layout a GridLayout si quieres una columna vertical
    
        // Recorrer los productos y agregar los paneles correspondientes
        //int id = 0;
        for (Producto producto : productos) {
            PanelProductoAgregar panelProducto = new PanelProductoAgregar();
            panelProducto.setjPanelPadre(panel7.getjPanel2());  // Establecemos el panel padre para que el producto se agregue allí
            panelProducto.setPanel7(panel7);
            Controlador controlador = new Controlador(panelProducto);
            controlador.cargarProductoAgregarOrdenCompra(producto); // Cargamos los datos del producto en el panel
            panel7.getjPanel2().add(panelProducto);  // Agregamos el panel de producto al jPanel1
            panel7.getPaneles().add(panelProducto);
        }
    
        panel7.getjPanel2().revalidate();  // Refrescar la interfaz
        panel7.getjPanel2().repaint();
        
    }
    
    public void filtrarProductosOrdenCompra() {
    String texto = panel7.getjTextField2().getText().toLowerCase();
    panel7.getjPanel2().removeAll(); // Limpiar el panel

    // Obtener los productos filtrados desde la base de datos
    List<Producto> productosFiltrados = Producto.listarProductosFiltrados(texto);

    // Recorrer los productos filtrados y agregar los paneles correspondientes
    for (Producto producto : productosFiltrados) {
        PanelProductoAgregar panelProducto = new PanelProductoAgregar();
        panelProducto.setjPanelPadre(panel7.getjPanel2());  // Establecemos el panel padre
        panelProducto.setPanel7(panel7);
        Controlador controlador = new Controlador(panelProducto);
        controlador.cargarProductoAgregarOrdenCompra(producto); // Cargamos los datos del producto
        panel7.getjPanel2().add(panelProducto);  // Agregamos el panel de producto al jPanel1
    }

    panel7.getjPanel2().revalidate();  // Refrescar la interfaz
    panel7.getjPanel2().repaint();
}
    
      //Agregar prodicto a la lista de la orden de compra
    public void agregarProductoOrdenCompra(){
        
        if(verificarCampoNumerico(panel8.getjTextField1())){
            int cantidad = Integer.parseInt(panel8.getjTextField1().getText());
            int cantidadacumulativa = panel8.getCantidadacumulativa()+cantidad;
            if(panel8.getCantidadtotal()-cantidadacumulativa > 0){
                Object []filas = new Object[3];
                filas[0] = panel8.getjLabel8().getText();
                filas[1] = panel8.getjLabel9().getText();
                filas[2] = Integer.toString(cantidad);//panel8.getjLabel13().getText();
                
                Producto producto = new Producto();
                producto.setNombre_producto(panel8.getjLabel8().getText());
                producto.setPrecio(Integer.parseInt(panel8.getjLabel9().getText()));
                producto.setCantidad(cantidad);
                producto.setId(panel8.getId());
                
                panel8.setCantidadacumulativa(cantidadacumulativa);
                panel8.getjLabel13().setText(Integer.toString(panel8.getCantidadtotal()-cantidadacumulativa));
                panel8.getPanel7().getModelo1().addRow(filas);
                panel8.getPanel7().getjTable1().setModel(panel8.getPanel7().getModelo1());
                
                panel8.getPanel7().getProductosagregados().add(producto);
                
            }else{
                JOptionPane.showMessageDialog(null, "La cantidad a retirar no puede ser mayor a la cantidad total del producto.");
            }
        }
        
        
        
        
    }
    
    public void eliminarProductoOrdenCompra(){
        if(panel7.getModelo1().getRowCount() > 0){
            for(int i = 0 ;i < panel7.getProductosagregados().size()-1;i++){
                if((panel7.getPaneles().get(i).getId() == panel7.getProductosagregados().get(panel7.getProductosagregados().size()-1).getId())){
                    panel7.getPaneles().get(i).setCantidadacumulativa(panel7.getPaneles().get(i).getCantidadacumulativa()-panel7.getProductosagregados().get(panel7.getProductosagregados().size()-1).getCantidad());
                    panel7.getPaneles().get(i).getjLabel13().setText(Integer.toString(panel7.getPaneles().get(i).getCantidadtotal()-panel7.getPaneles().get(i).getCantidadacumulativa()));
                    System.out.print("entro");
                }
                
            }
            panel7.getModelo1().removeRow(panel7.getModelo1().getRowCount()-1);
            panel7.getProductosagregados().remove(panel7.getProductosagregados().size()-1);
            panel5.getjPanelPadre().revalidate();
    //panel5.getjPanelPadre().repaint();f
        }
                     
    }
    //--
}
