package com.tienda.Controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String Listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        
            model.addAttribute("totalproductos", productos.size());

            var categorias = categoriaService.getCategorias(false);
            model.addAttribute("categorias", categorias);

            return "/pruebas/listado";
        }
    
    @GetMapping("/listado2")
    public String Listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        
            return "/pruebas/listado2";
        }
    
    @PostMapping("/query1")
    public String consulta1 (@RequestParam(value="precioInf") double precioInf,
            @RequestParam(value="precioSub") double precioSub,Model model){
        var productos = productoService.consultaQuery(precioInf, precioSub);
        model.addAttribute("productos", productos);
       
        return "/pruebas/listado2";

        }
    
    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
        
    }

}