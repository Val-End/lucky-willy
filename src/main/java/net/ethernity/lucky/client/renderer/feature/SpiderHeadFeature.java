package net.ethernity.lucky.client.renderer.feature;

import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LimbAnimator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SpiderHeadFeature<T extends LivingEntity, M extends EntityModel<T> & ModelWithHead> extends FeatureRenderer<T, M> {
    private final Map<SkullBlock.SkullType, SkullBlockEntityModel> headModels;

    public SpiderHeadFeature(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.headModels = SkullBlockEntityRenderer.getModels(loader);
    }

    @Override
    public void render(MatrixStack matrix, VertexConsumerProvider vcp, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (stack.isEmpty())
            return;

        matrix.push();
        this.getContextModel().getHead().rotate(matrix);

        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock skullBlock) {
            matrix.scale(1.1875F, -1.1875F, -1.1875F);
            matrix.translate(-0.5, -0.25f, -0.25);

            LimbAnimator limbAnimator = livingEntity.limbAnimator;
            if (livingEntity.getVehicle() instanceof LivingEntity vehicle)
                limbAnimator = vehicle.limbAnimator;

            float progress = limbAnimator.getPos(h);

            SkullBlock.SkullType skullType = skullBlock.getSkullType();
            RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(skullType, stack.get(DataComponentTypes.PROFILE));
            SkullBlockEntityModel skullModel = this.headModels.get(skullType);

            SkullBlockEntityRenderer.renderSkull(null, 180.0F, progress, matrix, vcp, i, skullModel, renderLayer);
        }

        matrix.pop();
    }
}
