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
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LimbAnimator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SpiderHeadFeature<T extends LivingEntity, M extends EntityModel<T> & ModelWithHead> extends FeatureRenderer<T, M> {
    private final Map<SkullBlock.SkullType, SkullBlockEntityModel> headModels;
    private final HeldItemRenderer heldItemRenderer;

    public SpiderHeadFeature(FeatureRendererContext<T, M> context, EntityModelLoader loader, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.headModels = SkullBlockEntityRenderer.getModels(loader);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            matrixStack.push();
            this.getContextModel().getHead().rotate(matrixStack);

            if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
                matrixStack.scale(1.1875F, -1.1875F, -1.1875F);
                matrixStack.translate(0, -0.25f, 0.25f);

                ProfileComponent profileComponent = itemStack.get(DataComponentTypes.PROFILE);
                matrixStack.translate(-0.5, 0.0, -0.5);
                SkullBlock.SkullType skullType = ((AbstractSkullBlock)((BlockItem)item).getBlock()).getSkullType();
                SkullBlockEntityModel skullBlockEntityModel = (SkullBlockEntityModel)this.headModels.get(skullType);
                RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(skullType, profileComponent);
                LimbAnimator limbAnimator;
                if (livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
                    limbAnimator = livingEntity2.limbAnimator;
                } else {
                    limbAnimator = livingEntity.limbAnimator;
                }

                float o = limbAnimator.getPos(h);
                SkullBlockEntityRenderer.renderSkull(null, 180.0F, o, matrixStack, vertexConsumerProvider, i, skullBlockEntityModel, renderLayer);
            }

            matrixStack.pop();
        }
    }
}
