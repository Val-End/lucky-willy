package net.ethernity.lucky.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import org.spongepowered.asm.mixin.*;

@Mixin(SpiderEntityModel.class)
@Implements(@Interface(iface = ModelWithHead.class, prefix = "head$"))
public abstract class SpiderModelMixin {
    @Mutable
    @Final
    @Shadow
    private final ModelPart head;

    private SpiderModelMixin(ModelPart head) {
        this.head = head;
    }

    public ModelPart head$getHead() {
        return this.head;
    }
}
