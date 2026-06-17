package com.example.imperfect.core.database.seed

import com.example.imperfect.core.database.dao.LookupDao
import com.example.imperfect.core.database.entity.CareTimeSlotEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisStatusEntity
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity
import com.example.imperfect.core.database.entity.SkincareCategoryEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity

class LookupSeeder(
    private val dao: LookupDao
) {

    suspend fun seed() {

        dao.insertViewTypes(
            listOf(
                PhotoViewTypeEntity(
                    id = 1,
                    code = "FRONT",
                    name = "Front view"
                ),
                PhotoViewTypeEntity(
                    id = 2,
                    code = "LEFT",
                    name = "Left view"
                ),
                PhotoViewTypeEntity(
                    id = 3,
                    code = "RIGHT",
                    name = "Right view"
                )
            )
        )

        dao.insertPhotoAnalysisStatuses(
            listOf(
                PhotoAnalysisStatusEntity(
                    id = 1,
                    code = "PENDING",
                    name = "Pending"
                ),
                PhotoAnalysisStatusEntity(
                    id = 2,
                    code = "COMPLETED",
                    name = "Completed"
                ),
                PhotoAnalysisStatusEntity(
                    id = 3,
                    code = "FAILED",
                    name = "Failed"
                )
            )
        )

        dao.insertSkincareCategories(
            listOf(
                // Очищение
                //SkincareCategoryEntity(1, "Очищение", "CLEANSER"),
                SkincareCategoryEntity(2, "Гель", "GEL"),
                //SkincareCategoryEntity(3, "Пенка", "FOAM"),
                //SkincareCategoryEntity(4, "Гидрофильное масло", "OIL_CLEANSER"),
                //SkincareCategoryEntity(5, "Мицеллярная вода", "MICELLAR"),

                // Подготовка
                SkincareCategoryEntity(6, "Тонер", "TONER"),
                SkincareCategoryEntity(7, "Лосьон", "LOTION"),
                //SkincareCategoryEntity(8, "Эссенция", "ESSENCE"),

                // Активы
                SkincareCategoryEntity(9, "Сыворотка", "SERUM"),
                //SkincareCategoryEntity(10, "Ампула", "AMPOULE"),
                //SkincareCategoryEntity(11, "Лечебное средство", "TREATMENT"),

                //Увлажнение
                SkincareCategoryEntity(12, "Крем", "CREAM"),
                //SkincareCategoryEntity(13, "Гель-крем", "GEL_CREAM"),
                SkincareCategoryEntity(14, "Эмульсия", "EMULSION"),
                SkincareCategoryEntity(15, "Бальзам", "BALM"),

                // Защита
                //SkincareCategoryEntity(16, "SPF", "SPF"),

                // Специальынй уход
                SkincareCategoryEntity(17, "Маска", "MASK"),
                //SkincareCategoryEntity(18, "Патчи", "PATCHES"),
                //SkincareCategoryEntity(19, "Пилинг", "PEELING"),
                //SkincareCategoryEntity(20, "Скраб", "SCRUB"),

                // Декор
                //SkincareCategoryEntity(21, "BB-крем", "BB_CREAM"),
                //SkincareCategoryEntity(22, "CC-крем", "CC_CREAM"),

                // Другое
                SkincareCategoryEntity(23, "Другое", "OTHER")
            )
        )

        dao.insertCareTimeSlots(
            listOf(
                CareTimeSlotEntity(
                    id = 1,
                    code = "MORNING",
                    name = "Утро"
                ),
                CareTimeSlotEntity(
                    id = 2,
                    code = "EVENING",
                    name = "Вечер"
                )
            )
        )

        dao.insertSkincareProducts(
            listOf(
                SkincareProductEntity(
                    id = 1,
                    name = "Hydrating Gel Cleanser",
                    brand = "Simple",
                    categoryId = 2,
                    description = "Мягкий гель для ежедневного очищения кожи. Бережно удаляет загрязнения, излишки себума и остатки макияжа, не нарушая естественный защитный барьер кожи. Подходит для утреннего и вечернего использования, оставляя ощущение свежести и комфорта после умывания.",
                    imagePath = null,
                    createdAt = "2026-01-01",
                    isFavorite = false
                ),

                SkincareProductEntity(
                    id = 2,
                    name = "Balancing Toner",
                    brand = "SomeByMi",
                    categoryId = 6,
                    description = "Балансирующий тонер помогает восстановить оптимальный уровень увлажнения после очищения кожи. Подготавливает кожу к последующим этапам ухода, способствует более эффективному впитыванию активных компонентов и дарит ощущение свежести без липкости.",
                    createdAt = "2026-01-01",
                    isFavorite = false
                ),

                SkincareProductEntity(
                    id = 3,
                    name = "Vitamin C Serum",
                    brand = "The Ordinary",
                    categoryId = 9,
                    description = "Осветляющая сыворотка с витамином C помогает выровнять тон кожи, уменьшить тусклость и придать лицу здоровое сияние. При регулярном использовании способствует сокращению видимости пигментных пятен и улучшает общий внешний вид кожи.",
                    createdAt = "2026-01-01",
                    isFavorite = true
                ),

                SkincareProductEntity(
                    id = 4,
                    name = "Moisturizing Cream",
                    brand = "CeraVe",
                    categoryId = 12,
                    description = "Увлажняющий крем с компонентами для восстановления защитного барьера кожи. Обеспечивает длительное увлажнение, помогает уменьшить ощущение сухости и стянутости, поддерживает комфорт кожи в течение всего дня.",
                    createdAt = "2026-01-01",
                    isFavorite = false
                ),

                SkincareProductEntity(
                    id = 5,
                    name = "Soothing Mask",
                    brand = "COSRX",
                    categoryId = 17,
                    description = "Успокаивающая тканевая маска с увлажняющими компонентами помогает быстро снять ощущение сухости и дискомфорта. Идеально подходит для использования после активного дня или в периоды, когда коже требуется дополнительное питание и восстановление.",
                    createdAt = "2026-01-01",
                    isFavorite = false
                )
            )
        )
    }
}