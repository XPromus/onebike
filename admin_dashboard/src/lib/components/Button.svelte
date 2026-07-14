<script lang="ts">
    import { button, type ButtonVariants } from "$lib/style/button.variants";
	import type { Snippet } from "svelte";
    
    interface Props extends ButtonVariants {
        children: Snippet;
        tooltip?: string;
        disabled?: boolean;
        onclick?: (e: MouseEvent) => void;
        class?: string;
    }

    let {
        variant,
        size,
        class: className,
        children,
        tooltip,
        disabled = false,
        onclick
    }: Props = $props();
</script>

<button
    onclick={onclick}
    disabled={disabled}
    title={tooltip}
    class={button({ variant, size, class: className })}
>
    {@render children()}
</button>
