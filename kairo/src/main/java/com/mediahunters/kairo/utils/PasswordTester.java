package com.mediahunters.kairo.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mediahunters.kairo.model.Usuario;
import com.mediahunters.kairo.repository.UsuarioRepository;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utilidad para probar contraseñas desde la consola.
 * IMPORTANTE: Comentar la anotación @Component cuando no se use para evitar que
 * se ejecute automáticamente.
 */
// @Component // Descomentar esta línea para activar la utilidad
public class PasswordTester implements CommandLineRunner {

    private final UsuarioRepository usuarioRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public PasswordTester(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========================================");
        System.out.println("   PROBADOR DE CONTRASEÑAS - KAIRO");
        System.out.println("========================================\n");

        // Mostrar todos los usuarios
        List<Usuario> usuarios = usuarioRepo.findAll();
        System.out.println("Usuarios en la base de datos:");
        System.out.println("------------------------------");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Email: " + u.getEmail() + " | Nombre: " + u.getNombre());
            String pass = u.getPassword();
            if (pass != null && pass.length() > 30) {
                pass = pass.substring(0, 30) + "...";
            }
            System.out.println("   Password: " + pass);
        }

        System.out.println("\n¿Qué deseas hacer?");
        System.out.println("1. Probar una contraseña para un usuario");
        System.out.println("2. Encriptar una nueva contraseña");
        System.out.println("3. Actualizar contraseña de un usuario");
        System.out.println("4. ELIMINAR DUPLICADOS (Fix Login)");
        System.out.print("\nOpción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                probarPassword(scanner);
                break;
            case 2:
                encriptarPassword(scanner);
                break;
            case 3:
                actualizarPassword(scanner);
                break;
            case 4:
                eliminarDuplicados(scanner);
                break;
            default:
                System.out.println("Opción inválida");
        }

        scanner.close();
    }

    private void probarPassword(Scanner scanner) {
        System.out.print("\nIngresa el email del usuario: ");
        String email = scanner.nextLine();

        // Nota: findByEmail puede fallar si hay duplicados, por eso agregamos la opción
        // 4
        try {
            Usuario usuario = usuarioRepo.findByEmail(email);
            if (usuario == null) {
                System.out.println("❌ Usuario no encontrado");
                return;
            }

            System.out.print("Ingresa la contraseña a probar: ");
            String password = scanner.nextLine();

            // Verificar si es texto plano o hash
            boolean matches = false;
            if (usuario.getPassword().startsWith("$2a$")) {
                matches = encoder.matches(password, usuario.getPassword());
            } else {
                matches = password.equals(usuario.getPassword());
            }

            if (matches) {
                System.out.println("\n✅ ¡CONTRASEÑA CORRECTA!");
                System.out.println("La contraseña es válida para " + email);
            } else {
                System.out.println("\n❌ CONTRASEÑA INCORRECTA");
                System.out.println("Hash/Valor almacenado: " + usuario.getPassword());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            System.out.println("⚠️ Probablemente hay usuarios duplicados. Usa la opción 4.");
        }
    }

    private void encriptarPassword(Scanner scanner) {
        System.out.print("\nIngresa la contraseña a encriptar: ");
        String password = scanner.nextLine();

        String hash = encoder.encode(password);

        System.out.println("\n✅ Contraseña encriptada:");
        System.out.println(hash);
    }

    private void actualizarPassword(Scanner scanner) {
        System.out.print("\nIngresa el email del usuario: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = usuarioRepo.findByEmail(email);
            if (usuario == null) {
                System.out.println("❌ Usuario no encontrado");
                return;
            }

            System.out.print("Ingresa la NUEVA contraseña: ");
            String nuevaPassword = scanner.nextLine();

            System.out.print("¿Estás seguro? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                // Guardar en texto plano por ahora (según configuración actual)
                usuario.setPassword(nuevaPassword);
                usuarioRepo.save(usuario);
                System.out.println("\n✅ Contraseña actualizada exitosamente!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            System.out.println("⚠️ Probablemente hay usuarios duplicados. Usa la opción 4.");
        }
    }

    private void eliminarDuplicados(Scanner scanner) {
        System.out.println("\n=== BUSCANDO DUPLICADOS ===");
        List<Usuario> usuarios = usuarioRepo.findAll();

        // Agrupar por email
        Map<String, List<Usuario>> porEmail = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getEmail));

        boolean encontrados = false;

        for (Map.Entry<String, List<Usuario>> entry : porEmail.entrySet()) {
            if (entry.getValue().size() > 1) {
                encontrados = true;
                String email = entry.getKey();
                System.out.println("\n⚠️ Duplicados encontrados para: " + email);

                List<Usuario> dups = entry.getValue();
                for (Usuario u : dups) {
                    System.out.println(
                            "   ID: " + u.getId() + " | Nombre: " + u.getNombre() + " | Pass: " + u.getPassword());
                }

                System.out.print("¿Deseas eliminar los duplicados y dejar solo el último? (S/N): ");
                String resp = scanner.nextLine();

                if (resp.equalsIgnoreCase("S")) {
                    // Ordenar por ID descendente (el último creado primero)
                    dups.sort((u1, u2) -> u2.getId().compareTo(u1.getId()));

                    // Mantener el primero (el último ID), borrar el resto
                    Usuario aMantener = dups.get(0);
                    for (int i = 1; i < dups.size(); i++) {
                        Usuario aBorrar = dups.get(i);
                        System.out.println("   🗑️ Eliminando ID: " + aBorrar.getId());
                        usuarioRepo.delete(aBorrar);
                    }
                    System.out.println("   ✅ Se mantuvo el ID: " + aMantener.getId());
                }
            }
        }

        if (!encontrados) {
            System.out.println("\n✅ No se encontraron usuarios duplicados.");
        }
    }
}
