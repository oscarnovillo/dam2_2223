package cliente.ui;

import io.reactivex.rxjava3.schedulers.Schedulers;
import cliente.data.CacheAuthorization;
import cliente.data.DaoEstupido;
import cliente.data.network.ConfigurationSingleton_OkHttpClient;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {


        CacheAuthorization ca = new CacheAuthorization();
        ca.setUser("user");
        ca.setPass("pass");


        DaoEstupido dao = new DaoEstupido(ca);

        dao.getAlumno()
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(either -> {
                    if (either.isRight()) {
                        System.out.println(either.get());

                    } else if (either.isLeft()) {
                        System.out.println(either.getLeft());

                    }


                });

//        CompletableFuture.runAsync(() -> {
//
//            DaoEstupido dao = new DaoEstupido(ca);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            dao.getAlumno().observeOn(Schedulers.single())
//                    .blockingSubscribe(either -> {
//                        if (either.isRight()) {
//                            System.out.println(either.get());
//
//                        } else if (either.isLeft()) {
//                            System.out.println(either.getLeft());
//
//                        }
//
//
//                    });
//
//            dao.getJwt().observeOn(Schedulers.single())
//                    .blockingSubscribe(either -> {
//                        if (either.isRight()) {
//
//                            System.out.println(either.get());
//                            dao.getVerify().observeOn(Schedulers.io())
//                                    .subscribe(either2 -> {
//                                        if (either2.isRight()) {
//                                            System.out.println(either2.get());
//
//                                        } else if (either2.isLeft()) {
//                                            System.out.println(either2.getLeft());
//
//                                        }
//
//
//                                    });
//
//
//                        } else if (either.isLeft()) {
//                            System.out.println(either.getLeft());
//
//                        }
//
//
//                    });
//
//
//        }).join();

        System.out.println("FIN");
        ConfigurationSingleton_OkHttpClient.clientOK.connectionPool().evictAll();
//        System.exit(-1);

    }


}
