package Controlador;


import Modelo.CasoSoporte;
import Modelo.ImageUtils;
import Modelo.Orden;
import Modelo.OrdenConDomicilio;
import Modelo.Producto;
import Vista.PanelCasoSoporte;
import Vista.PanelConsultaProducto;
import Vista.PanelConsultarCasoSoporte;
import Vista.PanelConsultarOrden;
import Vista.PanelConsultarOrdenConDomicilio;
import Vista.PanelOrden;
import Vista.PanelOrdenConDomicilio;
import Vista.PanelProducto;
import Vista.PanelProductoAgregar;
import Vista.PanelRegistrarCasoSoporte;
import Vista.PanelRegistrarOrdenCompra;
import Vista.PanelRegistrarProducto;
import Vista.PantallaModificarCasoSoporte;
import Vista.PantallaModificarOrden;
import Vista.PantallaModificarOrdenConDomicilio;
import Vista.PantallaModificarProducto;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
public class Controlador {
    //Atributos
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
    PanelConsultarOrden panel9;
    PanelOrden panel10;
    PantallaModificarOrden pantalla3;
    
    PanelOrdenConDomicilio panel11;
    PanelConsultarOrdenConDomicilio panel12;
    PantallaModificarOrdenConDomicilio pantalla4;
            
    //Controladores
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

    public Controlador(PanelConsultarOrden panel9) {
        this.panel9 = panel9;
    }

    public Controlador(PanelOrden panel10) {
        this.panel10 = panel10;
    }

    public Controlador(PantallaModificarOrden pantalla3) {
        this.pantalla3 = pantalla3;
    }

    public Controlador(PantallaModificarOrdenConDomicilio pantalla4) {
        this.pantalla4 = pantalla4;
    }

    public Controlador(PanelOrdenConDomicilio panel11) {
        this.panel11 = panel11;
    }

    public Controlador(PanelConsultarOrdenConDomicilio panel12) {
        this.panel12 = panel12;
    }
    
    //Metodos de validacion--
    private boolean verificarFecha(com.toedter.calendar.JDateChooser dateChooser) {
        return dateChooser.getDate() != null;
    }
    
    public boolean verificarCampo(JTextField jTextField) {
        String texto = jTextField.getText().trim(); //Elimina espacios al principio y al final
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este campo no puede estar vacío.");
            return false;
        }
        jTextField.setText(texto); //Actualiza el JTextField con el texto limpio
        return true;
    }
    
    public boolean verificarCampoNumerico(JTextField jTextField) {
        String texto = jTextField.getText().trim();
        try {
            //Intentamos parsear el texto como un número
            Double.parseDouble(texto);
            jTextField.setText(texto); //Actualiza el JTextField con el texto limpio
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo debe contener solo números.");
            return false;
        }
    }
    
    public boolean verificarTextArea(JTextArea jTextArea) {
        String texto = jTextArea.getText().trim(); //Elimina espacios al principio y al final
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de descripción no puede estar vacío.");
            return false;
        }
        jTextArea.setText(texto); //Actualiza el JTextArea con el texto limpio
        return true;
    }
    //--
    
    
    //Metodos para registrar un producto--
    public void registrarProductoregistro(){
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
    
    public void cargarImagenregistro() {
        //Abre un JFileChooser para seleccionar una imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        int result = fileChooser.showOpenDialog(panel1);
        //Validacion
        if (result == JFileChooser.APPROVE_OPTION) {
            //Obtener el archivo seleccionado
            //File imagenSeleccionada = panel1.getImagenSeleccionada();
            panel1.setImagenSeleccionada(fileChooser.getSelectedFile());

            try {
                //Redimensionar la imagen seleccionada
                BufferedImage resizedImage = ImageUtils.resizeImage(panel1.getImagenSeleccionada(), 200, 200); // Tamaño de 200x200
                //Mostrar la imagen redimensionada en el JLabel
                panel1.getjLabel8().setIcon(new javax.swing.ImageIcon(resizedImage));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(panel1, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}
    //--
    
    //Metodos de consulta de productos--
    public void cargarProductosconsulta(){
        //Obtener la lista de productos desde la base de datos
        List<Producto> productos = Producto.listarProductos();
        //Configuramos el layout para mostrar los productos
        //panel2.getjPanel1().setLayout(new GridLayout(0, 1, 10, 10)); //Ajusta el layout a GridLayout si quieres una columna vertical
        panel2.getjPanel1().setLayout(new BoxLayout(panel2.getjPanel1(), BoxLayout.Y_AXIS)); // Ajusta el layout a GridLayout si quieres una columna vertical
        //Recorrer los productos y agregar los paneles correspondientes
        for (Producto producto : productos) {
            PanelProducto panelProducto = new PanelProducto();
            panelProducto.setjPanelPadre(panel2.getjPanel1());  //Establecemos el panel padre para que el producto se agregue allí
            Controlador controlador = new Controlador(panelProducto);
            controlador.cargarProductoinvidualconsulta(producto); //Cargamos los datos del producto en el panel
            panel2.getjPanel1().add(panelProducto);  //Agregamos el panel de producto al jPanel1
            //Guardar referencia en una lista para manipularlos más adelante
            panel2.getPaneles().add(panelProducto);
        }  
        panel2.getjPanel1().revalidate();  //Refrescar la interfaz
        panel2.getjPanel1().repaint();     
    }
    
    public void filtrarProductosconsulta() {
        String texto = panel2.getjTextField1().getText().toLowerCase();
        // Recorremos los paneles existentes
        for (PanelProducto panelProducto : panel2.getPaneles()) {
            String nombreProducto = panelProducto.getjLabel8().getText().toLowerCase(); // Supongamos que getjLabelNombre() devuelve el nombre del producto.
            boolean visible = nombreProducto.contains(texto);

            // Mostramos u ocultamos el panel según el filtro
            panelProducto.setVisible(visible);
        }

        // Refrescamos el contenedor
        panel2.getjPanel1().revalidate();
        panel2.getjPanel1().repaint();
}
    //--

    //Metodos para un producto invidual--
    public void cargarProductoinvidualconsulta(Producto producto){
        //Cargar los datos del producto en los labels
        panel3.getjLabel8().setText(producto.getNombre_producto());
        panel3.getjLabel10().setText(producto.getDescripcion());
        panel3.getjLabel9().setText(Integer.toString(producto.getPrecio()));
        panel3.getjLabel11().setText(producto.getCategoria());
        panel3.getjLabel7().setText(producto.getMarca());
        panel3.getjLabel13().setText(Integer.toString(producto.getCantidad()));  // Aquí cargamos la cantidad
        //Cargar la imagen del producto (asegurándote de que el método getImagen() devuelva una ruta válida o una imagen en formato adecuado)
        //jLabel14 es el JLabel donde se mostrará la imagen
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenProducto = new ImageIcon(imagenBytes);
            panel3.getjLabel14().setIcon(imagenProducto);
        }   
        panel3.setId(producto.getId());       
    }
    
    public void abrirVentanaproductoedicion() throws IOException{
        // Abre la ventana
        PantallaModificarProducto pantalla = new PantallaModificarProducto();
        pantalla.setPantalla(panel3);
        //Crear el objeto Producto con los datos actuales del producto en la vista
        Producto producto = new Producto(
            panel3.getId(),
            panel3.getjLabel8().getText(),  //Nombre del producto
            panel3.getjLabel10().getText(), //Descripción del producto
            panel3.getjLabel11().getText(),  //Categoría del producto
            panel3.getjLabel7().getText(),  //Marca del producto
            Integer.parseInt(panel3.getjLabel9().getText()), // Precio del producto           
            Integer.parseInt(panel3.getjLabel13().getText()), // Cantidad del producto
            obtenerImagenBytesedicion() // Obtén los bytes de la imagen actual
        );    
        // Cargar los datos del producto en la ventana de modificación
        Controlador controlador = new Controlador(pantalla);//pantalla1);
        controlador.cargarProductoedicion(producto);
        //Mostrar la ventana
        pantalla.setVisible(true);
    }
    
    public byte[] obtenerImagenBytesedicion() {
        ImageIcon imagenIcon = (ImageIcon) panel3.getjLabel14().getIcon(); //Obtiene la imagen del JLabel

        if (imagenIcon != null) {
            Image image = imagenIcon.getImage(); //Obtén la imagen como Image
            //Crear un BufferedImage compatible
            BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB
            );
            //Dibujar la imagen original en el BufferedImage
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            //Convertir el BufferedImage a byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "jpg", baos);  //Cambia "jpg" según el formato deseado
                baos.flush();
                return baos.toByteArray();  //Retorna el byte[] de la imagen
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;  //Si no hay imagen, devuelve null
}

    public void eliminarProductoconsulta(){
        //Crear un objeto Producto con el id del producto que queremos eliminar
        Producto producto = new Producto();
        producto.setId(panel3.getId());
        //Eliminar el producto de la base de datos
        producto.eliminarProducto();
        //Eliminar el panel del producto de la vista principal (jPanelPadre)
        panel3.getjPanelPadre().remove(panel3);
        panel3.getjPanelPadre().revalidate();
        panel3.getjPanelPadre().repaint();
        //Mostrar un mensaje de éxito (opcional)
        JOptionPane.showMessageDialog(panel3, "Producto eliminado exitosamente");
    }
    //--
    
    //Metodos para modificar un producto--
     //Para cargar la imagen si el ususario selecciona otra en la edicion
    public void cargarImagenedicion() {
        //Abre un JFileChooser para seleccionar una imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        int result = fileChooser.showOpenDialog(pantalla1);

        if (result == JFileChooser.APPROVE_OPTION) {
            //Obtener el archivo seleccionado
            //File imagenSeleccionada = pantalla1.getImagenSeleccionada();
            //imagenSeleccionada = fileChooser.getSelectedFile();
            pantalla1.setImagenSeleccionada(fileChooser.getSelectedFile());

            try {
                // Redimensionar la imagen seleccionada
                BufferedImage resizedImage = ImageUtils.resizeImage(pantalla1.getImagenSeleccionada(), 200, 200); // Tamaño de 200x200
                // Mostrar la imagen redimensionada en el JLabel
                pantalla1.getjLabel8().setIcon(new javax.swing.ImageIcon(resizedImage));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(pantalla1, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}
    //Metodo para cargar un producto en la ventana en la que se editara
    public void cargarProductoedicion(Producto producto) throws IOException{
        pantalla1.getjTextField1().setText(producto.getNombre_producto());
        pantalla1.getjTextArea1().setText(producto.getDescripcion());
        pantalla1.getjTextField3().setText(Integer.toString(producto.getPrecio()));
        pantalla1.getjTextField6().setText(Integer.toString(producto.getCantidad()));
        pantalla1.getjTextField4().setText(producto.getCategoria());
        pantalla1.getjTextField5().setText(producto.getMarca());
        pantalla1.setId(producto.getId());
        //Cargar la imagen si existe
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenIcon = new ImageIcon(imagenBytes);
            pantalla1.getjLabel8().setIcon(imagenIcon);  //Asegúrate de tener un JLabel para mostrar la imagen 
            //Guardar imagen actual en imagenSeleccionada
            pantalla1.setImagenSeleccionada(ImageUtils.bytesToFile(imagenBytes, "imagen_actual.jpg"));
        }
    }
    
    //Para guardar los cambios del producto que estaba modificando en la ventana de edicion
    public void guardarCambiosproductoedicion() {
        try {
            //Obtener los valores ingresados en los campos
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
            //Crear un objeto Producto con los nuevos valores
            Producto producto = new Producto();
            producto.setId(pantalla1.getId());
            producto.setNombre_producto(nombre);
            producto.setDescripcion(descripcion);
            producto.setCategoria(categoria);
            producto.setMarca(marca);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            //Validar que haya una imagen seleccionada o cargada
            if (pantalla1.getImagenSeleccionada() == null) {
                JOptionPane.showMessageDialog(pantalla1, "Por favor, seleccione o cargue una imagen del producto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Convertir la imagen seleccionada a byte[]
            byte[] imagenBytes = ImageUtils.resizeImageToBytes(pantalla1.getImagenSeleccionada(), 200, 200);
            producto.setImagen(imagenBytes);
            //Llamar al método de actualización en la clase Producto
            producto.actualizarProducto();
            Controlador controlador = new Controlador(pantalla1.getPantalla());
            controlador.cargarProductoinvidualconsulta(producto);
            //Notificar éxito
            JOptionPane.showMessageDialog(pantalla1, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);           
            //Cerrar la ventana
            pantalla1.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(pantalla1, "Ingrese valores numéricos válidos para el precio y la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pantalla1, "Ocurrió un error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
    //--
    //Metodos para los casos de soporte--
    public void registrarCasosoporteregistro(){
        try {
            //Verificar que todos los campos sean válidos
            if (verificarCampo(panel4.getjTextField1()) && verificarTextArea(panel4.getjTextArea1()) && verificarFecha(panel4.getjDateChooser1())) {               
                //Crear el objeto CasoSoporte y asignar los datos
                CasoSoporte nuevoCaso = new CasoSoporte();
                nuevoCaso.setNombre_cliente(panel4.getjTextField1().getText());
                nuevoCaso.setDescripcion(panel4.getjTextArea1().getText());
                
                nuevoCaso.setFecha_registro(panel4.getjDateChooser1().getDate());
                nuevoCaso.setEstado(panel4.getjComboBox1().getSelectedItem().toString());
                
                //Aquí puedes guardar el caso en la base de datos
                nuevoCaso.guardarCaso();
                
                //Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(panel4, "Caso de soporte registrado exitosamente.");
                panel4.limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(panel4, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            //Manejo de errores
            JOptionPane.showMessageDialog(panel4, "Error al registrar el caso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Metodos para un caso de soporte invidual--
    public void cargarCasosoporteindividualconsulta(CasoSoporte casosoporte){
        //Cargar los datos del producto en los labels
        panel5.getjLabel8().setText(casosoporte.getNombre_cliente());
        panel5.getjLabel10().setText(casosoporte.getDescripcion());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //HH:mm::ss");
        panel5.getjLabel9().setText(formatter.format(casosoporte.getFecha_registro()));
        panel5.getjLabel13().setText(casosoporte.getEstado());
        panel5.setId(casosoporte.getId());
        
    }
    
    public void abrirVentanacasosoporteedicion() throws IOException, ParseException{
        // Abre la ventana
        PantallaModificarCasoSoporte pantalla = new PantallaModificarCasoSoporte();
        pantalla.setPantalla(panel5);
        // Crear el objeto Producto con los datos actuales del producto en la vista
        //
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        // Valida el contenido del label
        String fechaTexto = panel5.getjLabel9().getText();
        if (fechaTexto == null || fechaTexto.isEmpty()) {
            throw new ParseException("La fecha no puede estar vacía", 0);
        }
        
        // Convierte el texto a una fecha
        Date fecha = formato.parse(fechaTexto);//panel5.getjLabel9().getText());
        
        // Crea un Timestamp a partir de la fecha
        Timestamp timestamp = new Timestamp(fecha.getTime());
       
        CasoSoporte casosoporte = new CasoSoporte(
            panel5.getId(),
            panel5.getjLabel8().getText(),  // Nombre del cliente
            panel5.getjLabel10().getText(), 
            timestamp,  // Fecha de registro
            panel5.getjLabel13().getText()  // Estado del caso

        );    
        //Cargar los datos del producto en la ventana de modificación
        Controlador controlador = new Controlador(pantalla);//pantalla2);
        controlador.cargarCasosoporteedicion(casosoporte);   
        //Mostrar la ventana
        pantalla.setVisible(true);
    }

    public void eliminarCasosoporteconsulta(){
        //Crear un objeto Producto con el id del producto que queremos eliminar
        CasoSoporte casosoporte = new CasoSoporte();
        casosoporte.setId(panel5.getId());
        //Eliminar el producto de la base de datos
        casosoporte.eliminarCaso();
        //Eliminar el panel del producto de la vista principal (jPanelPadre)
        panel5.getjPanelPadre().remove(panel5);
        panel5.getjPanelPadre().revalidate();
        panel5.getjPanelPadre().repaint();
        // Mostrar un mensaje de éxito (opcional)
        JOptionPane.showMessageDialog(panel5, "Producto eliminado exitosamente");
    }
    //--
    
    //Metodos para modificar:
    public void cargarCasosoporteedicion(CasoSoporte casosoporte) throws IOException{
        // Verificar y setear el nombre del cliente
        pantalla2.getjTextField1().setText(casosoporte.getNombre_cliente());
        pantalla2.getjTextArea1().setText(casosoporte.getDescripcion());
        // Si `casosoporte.getFecha_registro()` es un objeto Date
        Date fecha = casosoporte.getFecha_registro();

        // Si necesitas convertirla a texto para algún propósito
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = dateFormat.format(fecha);
        // Setear la fecha en el JDateChooser
        pantalla2.getjDateChooser1().setDate(fecha);
        //System.out.println("Fecha en texto: " + fechaTexto);      
        pantalla2.getjComboBox1().setSelectedItem(casosoporte.getEstado());
        pantalla2.setId(casosoporte.getId());
        
    }
    
    public void guardarcambioscasosoporteedicion() {
        try {
            //Obtener los valores ingresados en los campos
            String nombre_cliente = pantalla2.getjTextField1().getText().trim();
            String descripcion = pantalla2.getjTextArea1().getText().trim();
            Date fecha_registro = pantalla2.getjDateChooser1().getDate();
            String estado = pantalla2.getjComboBox1().getSelectedItem().toString();
            //Validar que no haya campos vacíos
            if (nombre_cliente.isEmpty() || descripcion.isEmpty() || estado.isEmpty() || fecha_registro == null) {
                JOptionPane.showMessageDialog(pantalla2, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Crear un objeto Producto con los nuevos valores
            CasoSoporte casosoporte = new CasoSoporte();
            casosoporte.setId(pantalla2.getId());
            casosoporte.setNombre_cliente(nombre_cliente);
            casosoporte.setDescripcion(descripcion);
            casosoporte.setFecha_registro(fecha_registro);
            casosoporte.setEstado(estado);
            //Llamar al método de actualización en la clase Producto
            casosoporte.actualizarCaso();
            Controlador controlador = new Controlador(pantalla2.getPantalla());
            controlador.cargarCasosoporteindividualconsulta(casosoporte); //cargarCasoSoporteRegistrar
            // Notificar éxito
            JOptionPane.showMessageDialog(pantalla2, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);            
            // Cerrar la ventana
            pantalla2.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(pantalla2, "Ingrese valores numéricos válidos para el precio y la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pantalla2, "Ocurrió un error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Metodos de consulta de casos de soporte
    public void cargarCasosoporteconsulta(){
        //Obtener la lista de productos desde la base de datos
        List<CasoSoporte> casos = CasoSoporte.listarCasos();
        //Configuramos el layout para mostrar los productos
        panel6.getjPanel1().setLayout(new BoxLayout(panel6.getjPanel1(), BoxLayout.Y_AXIS)); //Ajusta el layout a GridLayout si quieres una columna vertical
        //Recorrer los productos y agregar los paneles correspondientes
        for (CasoSoporte caso : casos) {
            PanelCasoSoporte panelCasoSoporte = new PanelCasoSoporte();
            panelCasoSoporte.setjPanelPadre(panel6.getjPanel1());  //Establecemos el panel padre para que el producto se agregue allí
            Controlador controlador = new Controlador(panelCasoSoporte);
            controlador.cargarCasosoporteindividualconsulta(caso); //Cargamos los datos del producto en el panel - cargarCasoSoporteRegistrar
            panel6.getjPanel1().add(panelCasoSoporte);  //Agregamos el panel de producto al jPanel1
        }
        panel6.getjPanel1().revalidate();  //Refrescar la interfaz
        panel6.getjPanel1().repaint();       
    }
    
    public void filtrarCasossoporteconsulta() {
        String texto = panel6.getjTextField1().getText().toLowerCase();
        //Recorremos los paneles existentes
        for (PanelCasoSoporte panelCasoSoporte : panel6.getPaneles()) {
            String nombrecasosoporte = panelCasoSoporte.getjLabel8().getText().toLowerCase(); // Supongamos que getjLabelNombre() devuelve el nombre del producto.
            boolean visible = nombrecasosoporte.contains(texto);
            //Mostramos u ocultamos el panel según el filtro
            panelCasoSoporte.setVisible(visible);
        }
        //Refrescamos el contenedor
        panel6.getjPanel1().revalidate();
        panel6.getjPanel1().repaint();
       
    }
    //--
    
    //Metodos para agregar productos a una orden de compra en el registro--
      //Metodos para un producto invidual dentro del panel para poder seleccionar productos para la orden de compra
    public void cargarProductoseleccionordencompraregistro(Producto producto){
        //Cargar los datos del producto en los labels
        panel8.getjLabel8().setText(producto.getNombre_producto());
        panel8.getjLabel10().setText(producto.getDescripcion());
        panel8.getjLabel9().setText(Integer.toString(producto.getPrecio()));
        panel8.getjLabel11().setText(producto.getCategoria());
        panel8.getjLabel7().setText(producto.getMarca());
        panel8.getjLabel13().setText(Integer.toString(producto.getCantidad()));  // Aquí cargamos la cantidad
        panel8.setCantidadtotal(producto.getCantidad());
        //Cargar la imagen del producto (asegurándote de que el método getImagen() devuelva una ruta válida o una imagen en formato adecuado)
        //jLabel14 es el JLabel donde se mostrará la imagen
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenProducto = new ImageIcon(imagenBytes);
            panel8.getjLabel14().setIcon(imagenProducto);
        }    
        panel8.setId(producto.getId());   
    }
   
    //Metodo complementario
    public void cargarProductosseleccionordencompraregistro(){
        //Obtener la lista de productos desde la base de datos
        List<Producto> productos = Producto.listarProductos();   
        //Configuramos el layout para mostrar los productos
        //panel7.getjPanel2().setLayout(new GridLayout(0, 1, 10, 10)); //Ajusta el layout a GridLayout si quieres una columna vertical
        panel7.getjPanel2().setLayout(new BoxLayout(panel7.getjPanel2(), BoxLayout.Y_AXIS)); // Ajusta el layout a GridLayout si quieres una columna vertical
        // Recorrer los productos y agregar los paneles correspondientes
        //int id = 0;
        for (Producto producto : productos) {
            PanelProductoAgregar panelProducto = new PanelProductoAgregar();
            panelProducto.setjPanelPadre(panel7.getjPanel2());  //Establecemos el panel padre para que el producto se agregue allí
            panelProducto.setPanel7(panel7);
            Controlador controlador = new Controlador(panelProducto);
            controlador.cargarProductoseleccionordencompraregistro(producto); // Cargamos los datos del producto en el panel
            panel7.getjPanel2().add(panelProducto);  //Agregamos el panel de producto al jPanel1
            panel7.getPaneles().add(panelProducto);
            panelProducto.setTipo(1); //Para dar a entender que es para editar o regostrar
        }   
        panel7.getjPanel2().revalidate();  //Refrescar la interfaz
        panel7.getjPanel2().repaint();     
    }
    
    public void filtrarProductosordencompraregistro() {
        String texto = panel7.getjTextField2().getText().toLowerCase();
        //Recorremos todos los paneles existentes
        for (PanelProductoAgregar panelProducto : panel7.getPaneles()) {
            //Verificamos si el texto del filtro está contenido en el nombre del producto
            String nombreProducto = panelProducto.getjLabel8().getText().toLowerCase();
            boolean visible = nombreProducto.contains(texto);
            //Mostramos u ocultamos el panel según el filtro
            panelProducto.setVisible(visible);
        }
        //Refrescamos el panel contenedor
        panel7.getjPanel2().revalidate();
        panel7.getjPanel2().repaint();
       
    }
    
    //Agregar prodicto a la lista de la orden de compra
    public void agregarProductoordencompraregistro(){
        try {
            int cantidad = Integer.parseInt(panel8.getjTextField1().getText());
            int disponible = panel8.getCantidadtotal() - panel8.getCantidadacumulativa();

            if (cantidad <= 0 || cantidad > disponible) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida. Disponible: " + disponible);
                return;
            }

            Producto producto = new Producto();
            producto.setId(panel8.getId());
            producto.setNombre_producto(panel8.getjLabel8().getText());
            producto.setPrecio(Integer.parseInt(panel8.getjLabel9().getText()));
            producto.setCantidad(cantidad);

            panel8.setCantidadacumulativa(panel8.getCantidadacumulativa() + cantidad);
            panel8.getjLabel13().setText(String.valueOf(disponible - cantidad));

            Object[] fila = {producto.getNombre_producto(), producto.getPrecio(), cantidad};
            panel8.getPanel7().getModelo1().addRow(fila);
            panel8.getPanel7().getProductosagregados().add(producto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
        }
             
    }
   
    public void eliminarProductoordencompraregistro(){
        DefaultTableModel modelo = panel7.getModelo1();
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.");
            return;
        }

        int lastIndex = modelo.getRowCount() - 1;
        Producto productoEliminado = panel7.getProductosagregados().get(lastIndex);

        // Actualizar cantidades en el panel correspondiente
        for (PanelProductoAgregar panelProducto : panel7.getPaneles()) {
            if (panelProducto.getId() == productoEliminado.getId()) {
                int nuevaCantidadAcumulativa = panelProducto.getCantidadacumulativa() - productoEliminado.getCantidad();
                panelProducto.setCantidadacumulativa(nuevaCantidadAcumulativa);
                panelProducto.getjLabel13().setText(String.valueOf(panelProducto.getCantidadtotal() - nuevaCantidadAcumulativa));
                break;
            }
        }
        // Eliminar del modelo y de la lista
        modelo.removeRow(lastIndex);
        panel7.getProductosagregados().remove(lastIndex);
        panel7.getjTable1().revalidate();
        panel7.getjTable1().repaint();      
    }
    
    public void registrarOrdencompraregistro() {
        if (panel7.getProductosagregados().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos agregados a la orden.");
            return;
        }

        // Crear la orden
        Orden orden;
        if (panel7.getjRadioButton1().isSelected()) { // Orden con domicilio
            String direccion = panel7.getjTextField3().getText();
            String destinatario = panel7.getjTextField6().getText();
            String telefono = panel7.getjTextField4().getText();
            String estado = panel7.getjComboBox1().getSelectedItem().toString();

            orden = new OrdenConDomicilio(direccion, destinatario, telefono, estado);
        } else { // Orden estándar
            orden = new Orden();
        }

        orden.setCliente(panel7.getjTextField1().getText()); // Puedes obtener el cliente de algún campo
        orden.setFecha(panel7.getjDateChooser1().getDate());
        orden.setProductos(panel7.getProductosagregados());
        orden.calcularTotal();

        // Guardar en la base de datos
        orden.guardarOrden();
        //guardarOrdenEnBD(orden);

        // Limpieza de la interfaz
        panel7.getProductosagregados().clear();
        panel7.getModelo1().setRowCount(0);

        JOptionPane.showMessageDialog(null, "Orden registrada exitosamente.");
}
    //--
    
    //Metodos para consutas
    public void cargarOrdenesconsulta() {
        // Simula obtener las órdenes de compra desde la base de datos
        List<Orden> ordenes = Orden.listarOrdenes(); // Método que retorna una lista de órdenes
        panel9.getjPanel1().setLayout(new BoxLayout(panel9.getjPanel1(), BoxLayout.Y_AXIS)); // Layout vertical para los paneles
        // Itera sobre las órdenes y crea paneles individuales
        for (Orden orden : ordenes) {
            PanelOrden panelOrden = new PanelOrden(); // Asumiendo que PanelOrden es una clase creada para mostrar cada orden
            panelOrden.setjPanelPadre(panel9.getjPanel1());
            Controlador controlador = new Controlador(panelOrden);
            controlador.cargarOrdenindividualconsulta(orden, panelOrden); //Llenar el panel con datos de la orden
            panel9.getjPanel1().add(panelOrden); // Agregar el panel al contenedor principal
            panel9.getPaneles().add(panelOrden); // Guardar referencia en la lista para futuras manipulaciones
        }
        panel9.getjPanel1().revalidate();
        panel9.getjPanel1().repaint();
    }

    public void filtrarOrdenesconsulta() {
        String texto = panel9.getjTextField1().getText().toLowerCase(); //Obtener texto del campo de búsqueda
        //Recorre los paneles y verifica si coinciden con el texto
        for (PanelOrden panelOrden : panel9.getPaneles()) {
            String nombreOrden = panelOrden.getjLabel8().getText().toLowerCase(); // Asegúrate de que `getjLabelNombre()` devuelva un atributo identificador como el nombre o número de orden
            boolean visible = nombreOrden.contains(texto); // Verifica si el texto coincide

            panelOrden.setVisible(visible); // Oculta o muestra el panel según la coincidencia
        }

        panel9.getjPanel1().revalidate();
        panel9.getjPanel1().repaint();
    }
    
    public void cargarOrdenindividualconsulta(Orden orden, PanelOrden panelOrden) {
        //Establece los valores generales de la orden
        panelOrden.getjLabel8().setText(Integer.toString(orden.getId()));
        panelOrden.getjLabel10().setText(orden.getCliente());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //HH:mm::ss");
        panelOrden.getjLabel9().setText(formatter.format(orden.getFecha()));
        panelOrden.getjLabel13().setText(Double.toString(orden.getTotal()));
        //Asignar productos al panel
        panelOrden.setProductosagregados(orden.getProductos());
        //Cargar los productos en la tabla
        DefaultTableModel modelo = (DefaultTableModel) panelOrden.getjTable1().getModel();
        modelo.setRowCount(0); // Limpiar la tabla existente
        for (Producto producto : orden.getProductos()) {
            modelo.addRow(new Object[]{
                producto.getNombre_producto(),
                producto.getCantidad(),
                producto.getPrecio()
            });
        }
        // Asociar el ID de la orden al panel
        panelOrden.setId(orden.getId());
    }

    //--
    
        public void eliminarOrdenconsulta() {
        // Crear un objeto de la orden de compra con el id actual
        Orden orden = new Orden();
        orden.setId(panel10.getId()); // Obtén el ID de la orden desde el panel

        // Iterar sobre cada producto en la orden
        for (Producto producto : panel10.getProductosagregados()) {
            producto.setCantidad(producto.getCantidad()); // Seteamos la cantidad a restablecer
            producto.devolverCantidadProducto(); // Método para actualizar la cantidad
        }

        // Eliminar la orden de la base de datos
        orden.eliminarOrden(); // Implementa un método similar a eliminarCaso() en OrdenCompra

        // Eliminar el panel de la vista
        panel10.getjPanelPadre().remove(panel10);
        panel10.getjPanelPadre().revalidate();
        panel10.getjPanelPadre().repaint();

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(panel10, "Orden de compra eliminada exitosamente.");
}


    //--
        
    //Metodos para editar una orden de compra--   
    //----
    //Metodos para agregar productos a una orden de compra en el registro--
      //Metodos para un producto invidual dentro del panel para poder seleccionar productos para la orden de compra
    public void cargarProductoseleccionordencompraedicion(Producto producto){
        //Cargar los datos del producto en los labels
        panel8.getjLabel8().setText(producto.getNombre_producto());
        panel8.getjLabel10().setText(producto.getDescripcion());
        panel8.getjLabel9().setText(Integer.toString(producto.getPrecio()));
        panel8.getjLabel11().setText(producto.getCategoria());
        panel8.getjLabel7().setText(producto.getMarca());
        panel8.getjLabel13().setText(Integer.toString(producto.getCantidad()));  // Aquí cargamos la cantidad
        panel8.setCantidadtotal(producto.getCantidad());
        //Cargar la imagen del producto (asegurándote de que el método getImagen() devuelva una ruta válida o una imagen en formato adecuado)
        //jLabel14 es el JLabel donde se mostrará la imagen
        byte[] imagenBytes = producto.getImagen();
        if (imagenBytes != null) {
            ImageIcon imagenProducto = new ImageIcon(imagenBytes);
            panel8.getjLabel14().setIcon(imagenProducto);
        }    
        panel8.setId(producto.getId());      
    }
   
    //Metodo complementario
    public void cargarProductosseleccionordencompraredicion(){
        //Obtener la lista de productos desde la base de datos
        List<Producto> productos = Producto.listarProductos();   
        //Configuramos el layout para mostrar los productos
        //panel7.getjPanel2().setLayout(new GridLayout(0, 1, 10, 10)); //Ajusta el layout a GridLayout si quieres una columna vertical
        pantalla3.getjPanel3().setLayout(new BoxLayout(pantalla3.getjPanel3(), BoxLayout.Y_AXIS)); // Ajusta el layout a GridLayout si quieres una columna vertical
        // Recorrer los productos y agregar los paneles correspondientes
        //int id = 0;
        for (Producto producto : productos) {
            PanelProductoAgregar panelProducto = new PanelProductoAgregar();
            panelProducto.setjPanelPadre(pantalla3.getjPanel3());  //Establecemos el panel padre para que el producto se agregue allí
            panelProducto.setPantalla1(pantalla3);
            panelProducto.setTipo(2);
            Controlador controlador = new Controlador(panelProducto);
            
            controlador.cargarProductoseleccionordencompraedicion(producto); // Cargamos los datos del producto en el panel
           pantalla3.getjPanel3().add(panelProducto);  //Agregamos el panel de producto al jPanel1
           pantalla3.getPaneles().add(panelProducto);
        }   
        pantalla3.getjPanel3().revalidate();  //Refrescar la interfaz
        pantalla3.getjPanel3().repaint();     
    }
    
    public void filtrarProductosordencompraredicion() {
        String texto = pantalla3.getjTextField2().getText().toLowerCase();
        //Recorremos todos los paneles existentes
        for (PanelProductoAgregar panelProducto : pantalla3.getPaneles()) {
            //Verificamos si el texto del filtro está contenido en el nombre del producto
            String nombreProducto = panelProducto.getjLabel8().getText().toLowerCase();
            boolean visible = nombreProducto.contains(texto);
            //Mostramos u ocultamos el panel según el filtro
            panelProducto.setVisible(visible);
        }
        //Refrescamos el panel contenedor
        pantalla3.getjPanel3().revalidate();
        pantalla3.getjPanel3().repaint();
       
    }
    
    //Agregar prodicto a la lista de la orden de compra
    public void agregarProductoordencompraredicion(){                           //--------------------------------
        try {
            int cantidad = Integer.parseInt(panel8.getjTextField1().getText());
            int disponible = panel8.getCantidadtotal() - panel8.getCantidadacumulativa();

            if (cantidad <= 0 || cantidad > disponible) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida. Disponible: " + disponible);
                return;
            }

            Producto producto = new Producto();
            producto.setId(panel8.getId());
            producto.setNombre_producto(panel8.getjLabel8().getText());
            producto.setPrecio(Integer.parseInt(panel8.getjLabel9().getText()));
            producto.setCantidad(cantidad);

            panel8.setCantidadacumulativa(panel8.getCantidadacumulativa() + cantidad);
            panel8.getjLabel13().setText(String.valueOf(disponible - cantidad));

            Object[] fila = {producto.getNombre_producto(), producto.getPrecio(), cantidad};
            panel8.getPantalla1().getModelo1().addRow(fila);
            panel8.getPantalla1().getProductosagregados().add(producto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
        }
             
    }
   
    public void eliminarProductoordencompraredicion(){
        DefaultTableModel modelo = pantalla3.getModelo1();
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.");
            return;
        }

        // Verificar si hay filas en la tabla
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.");
            return;
        }

        // Verificar si hay productos en la lista
        if (pantalla3.getProductosagregados().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista para eliminar.");
            return;
        }
        
        int lastIndex = modelo.getRowCount() - 1;
        Producto productoEliminado = pantalla3.getProductosagregados().get(lastIndex);

        // Actualizar cantidades en el panel correspondiente
        for (PanelProductoAgregar panelProducto : pantalla3.getPaneles()) {
            if (panelProducto.getId() == productoEliminado.getId()) {
                int nuevaCantidadAcumulativa = panelProducto.getCantidadacumulativa() - productoEliminado.getCantidad();
                panelProducto.setCantidadacumulativa(nuevaCantidadAcumulativa);
                panelProducto.getjLabel13().setText(String.valueOf(panelProducto.getCantidadtotal() - nuevaCantidadAcumulativa));
                break;
            }
        }
        // Eliminar del modelo y de la lista
        modelo.removeRow(lastIndex);
        pantalla3.getProductosagregados().remove(lastIndex);
        pantalla3.getjTable1().revalidate();
        pantalla3.getjTable1().repaint();      
    }
    
    public void actualizarOrdencompraedicion() {
        if (pantalla3.getProductosagregados().isEmpty()) {
            JOptionPane.showMessageDialog(pantalla3, "No hay productos agregados a la orden.");
            return;
        } 
        
        // Recorrer los paneles de productos y actualizar sus valores
        for (PanelProductoAgregar panelProducto : pantalla3.getPaneles()) {
            Producto producto = new Producto();
            producto.setId(panelProducto.getId());
            producto.setCantidad(Integer.parseInt(panelProducto.getjLabel13().getText()));
            // Añadir el producto actualizado a la lista
            producto.cambiarCantidadProducto();
            //productosActualizados.add(producto);
        }
        
        // Crear la orden con los datos actualizados
        Orden orden = new Orden();
        orden.setId(pantalla3.getId());
        orden.setCliente(pantalla3.getjTextField1().getText());
        orden.setFecha(pantalla3.getjDateChooser1().getDate());
        orden.setProductos(pantalla3.getProductosagregados());
        orden.calcularTotal(); // Actualiza el total basado en los productos
        
        
        
        
        
        if (pantalla3.getProductosagregados().isEmpty()) {
            JOptionPane.showMessageDialog(pantalla3, "No hay productos agregados a la orden.");
            return;
        }

        orden.actualizarOrden();
        // Limpieza de la interfaz
        pantalla3.getModelo1().setRowCount(0);
        JOptionPane.showMessageDialog(pantalla3, "Orden actualizada exitosamente.");
        Controlador controlador = new Controlador();
        controlador.cargarOrdenindividualconsulta(orden, pantalla3.getPantalla());
        pantalla3.getProductosagregados().clear();
        pantalla3.dispose();
        
}
    //--
    //----
     //Metodo para cargar un producto en la ventana en la que se editara
    public void cargarOrdenedicion(Orden orden) throws IOException{
        //Establece los valores generales de la orden
        pantalla3.getjTextField1().setText(orden.getCliente());
        pantalla3.getjDateChooser1().setDate(orden.getFecha());
        //Asignar productos al panel
        //pantalla3.setProductosagregados(orden.getProductos());
        //Cargar los productos en la tabla
        DefaultTableModel modelo = (DefaultTableModel) pantalla3.getjTable1().getModel();
        modelo.setRowCount(0); // Limpiar la tabla existente
        for (Producto producto : orden.getProductos()) {
            modelo.addRow(new Object[]{
                producto.getNombre_producto(),
                producto.getCantidad(),
                producto.getPrecio()
            });
        }
        // Asociar el ID de la orden al panel
        pantalla3.setId(orden.getId());
        pantalla3.setProductosagregados(orden.getProductos());
    }
    
    public void abrirVentanaordenedicion() throws IOException, ParseException{     
        //
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");     
        // Valida el contenido del label
        String fechaTexto = panel10.getjLabel9().getText();
        if (fechaTexto == null || fechaTexto.isEmpty()) {
            throw new ParseException("La fecha no puede estar vacía", 0);
        }
        
        // Convierte el texto a una fecha
        Date fecha = formato.parse(fechaTexto);//panel5.getjLabel9().getText());      
        // Crea un Timestamp a partir de la fecha
        Timestamp timestamp = new Timestamp(fecha.getTime());
       
        // Abre la ventana
        PantallaModificarOrden pantalla = new PantallaModificarOrden();
        pantalla.setPantalla(panel10);
        
        //Crear el objeto Orde con los datos actuales del producto en la vista
        Orden orden = new Orden (
                panel10.getId(),
                panel10.getjLabel10().getText(),
                timestamp,
                Double.parseDouble(panel10.getjLabel13().getText())                     
        );
        orden.setProductos(panel10.getProductosagregados());
            
        // Cargar los datos del producto en la ventana de modificación
        Controlador controlador = new Controlador(pantalla);//pantalla1);
        controlador.cargarOrdenedicion(orden);
        //Mostrar la ventana
        pantalla.setVisible(true);
    }

    //Metodo para agregar productos a una orden de compra segun si el panel PanelProductoAgregar donde este, si en registro, edicion, etc.
    public void agregarProductoordenpornumero(int tipo){
        switch (tipo) {
            case 1:
                agregarProductoordencompraregistro();
                break;
            case 2:
                agregarProductoordencompraredicion();
                break;        
            default:
                System.out.println("El número no está en el rango de 1 a 4.");
                break;
        }
        
        
    }

//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
    
    
    
}


